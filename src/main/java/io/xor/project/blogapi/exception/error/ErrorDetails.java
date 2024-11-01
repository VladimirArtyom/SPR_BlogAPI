package io.xor.project.blogapi.exception.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class ErrorDetails {
    private LocalDateTime timestamp;
    private String detail;

    public ErrorDetails(LocalDateTime timestamp, String detail) {
        this.timestamp = timestamp;
        this.detail = detail;
    }
}
