package com.sparta.scheduleproject.responseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleResponse {
    String msg;
    Object data;
}
