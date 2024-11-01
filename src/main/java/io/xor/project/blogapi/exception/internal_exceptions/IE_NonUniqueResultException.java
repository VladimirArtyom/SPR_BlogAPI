package io.xor.project.blogapi.exception.internal_exceptions;

import lombok.Getter;

@Getter
public class IE_NonUniqueResultException extends RuntimeException {
    private final String resource_name;
    private final String short_message;
    private final String exception_message;
    public IE_NonUniqueResultException(String resource_name, String short_message, String exception_message) {
        super(String.format("Resource %s : %s \n\n Detail: %s", resource_name, short_message, exception_message));
        this.short_message = short_message;
        this.resource_name = resource_name;
        this.exception_message = exception_message;
    }
}
