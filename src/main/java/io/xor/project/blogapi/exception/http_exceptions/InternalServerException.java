package io.xor.project.blogapi.exception.http_exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class InternalServerException extends RuntimeException{
    private String errorDetail;
    public InternalServerException(String errorDetail) {
        super("Internal Server Error");
        this.errorDetail = errorDetail;
    }
}
