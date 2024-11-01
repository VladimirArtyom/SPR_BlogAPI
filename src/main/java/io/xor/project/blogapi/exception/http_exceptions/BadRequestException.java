package io.xor.project.blogapi.exception.http_exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super(String.format("Bad Request: Your request cannot be processed"));
    }
}
