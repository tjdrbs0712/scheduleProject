package com.sparta.scheduleproject.responseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponse {
    String msg;
    Object data;
}
