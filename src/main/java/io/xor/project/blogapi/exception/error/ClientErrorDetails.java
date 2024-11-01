package io.xor.project.blogapi.exception.error;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ClientErrorDetails extends ErrorDetails{
    private String url;
    private String method;
    public ClientErrorDetails(String url, String method, LocalDateTime timestamp, String detail) {
        super(timestamp, detail);
        this.url = url;
        this.method = method;
    }
}
