package io.xor.project.blogapi.exception.internal_exceptions;

public class IE_PersistenceException extends  RuntimeException{
    public IE_PersistenceException(String resource, String message) {
        super(String.format("Resource %s : %s", resource, message));
    }
}
