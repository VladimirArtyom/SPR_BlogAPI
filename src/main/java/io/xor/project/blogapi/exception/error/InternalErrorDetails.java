package io.xor.project.blogapi.exception.error;

import java.time.LocalDateTime;

public class InternalErrorDetails extends ErrorDetails{
    public InternalErrorDetails(LocalDateTime timestamp, String detail) {
        super(timestamp, detail);
    }
}
