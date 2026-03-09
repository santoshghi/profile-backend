package com.profile.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiResponse {

    private HttpStatus status;
    private String message;
    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();
    private Object data;
}
