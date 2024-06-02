package com.sparta.scheduleproject.jwt;

import com.sparta.scheduleproject.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 리프레시 헤더 값
    public static final String REFRESH_HEADER = "Refresh-Token";
    // 사용자 권한
    public static final String AUTHORIZATION_KEY = "auth";
    // Token 식별자
    public static final String BEAR = "Bearer ";
    // 토큰 만료시간
    private final long TOKEN_TIME = 30 * 1000L; // 1시간
    // 리프레시 토큰 만료시간
    private final long REFRESH_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일

    // JWT secret key
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.secret.refresh-key}")
    private String refreshSecretKey;
    private Key accessKey;
    private Key refreshKey;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] accessKeyBytes = Base64.getDecoder().decode(secretKey);
        accessKey = Keys.hmacShaKeyFor(accessKeyBytes);

        byte[] refreshKeyBytes = Base64.getDecoder().decode(refreshSecretKey);
        refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }

    // 토큰 생성 공통 로직
    private String createToken(String subject, UserRoleEnum role, long expirationTime, Key signingKey) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey, signatureAlgorithm);

        if (role != null) {
            builder.claim(AUTHORIZATION_KEY, role);
        }


        return BEAR + builder.compact();
    }

    // 액세스 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        return createToken(username, role, TOKEN_TIME, accessKey);
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(String username, UserRoleEnum role) {
        return createToken(username, role, REFRESH_TOKEN_TIME, refreshKey);
    }

    // header에서 JWT 가져오기
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEAR)) {
            return bearerToken.substring(BEAR.length());
        }
        return null;
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        return validateTokenInternal(token, accessKey);
    }

    // 리프레시 토큰 검증
    public boolean validateRefreshToken(String token) {
        return validateTokenInternal(token, refreshKey);
    }

    // 토큰 검증 공통 로직
    private boolean validateTokenInternal(String token, Key signingKey) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.", e);
        } catch (Exception e){
            log.error("잘못되었습니다.", e);
        }
        return false;
    }

    // 토큰에서 username 가져오기
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 리프레시 토큰에서 username 가져오기
    public String getUsernameFromRefreshToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // 리프레시 토큰을 사용하여 새로운 액세스 토큰 발급
    public String refreshAccessToken(String refreshToken) {
        if (validateRefreshToken(refreshToken)) {
            String username = getUsernameFromRefreshToken(refreshToken);
            // 여기에서 필요한 경우 사용자 역할 정보를 가져올 수 있다.
            return createToken(username, null, TOKEN_TIME, accessKey); // 사용자 역할이 필요하면 두 번째 인자에 역할을 전달
        }

        return null;
    }
}
