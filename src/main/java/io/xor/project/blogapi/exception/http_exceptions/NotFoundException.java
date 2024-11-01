package io.xor.project.blogapi.exception.http_exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException{
    private final String resource_name;
    private final String resource_column;
    private final String resource_value;
    public NotFoundException(String resource_name, String resource_column, String resource_value) {
        super(String.format("Resource %s with %s column and %s value is not found",
                resource_name,
                resource_column,
                resource_value));
        this.resource_name  = resource_name;
        this.resource_column = resource_column;
        this.resource_value = resource_value;
    }

}
