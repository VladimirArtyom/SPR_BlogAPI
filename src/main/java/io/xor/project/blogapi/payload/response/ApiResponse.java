package io.xor.project.blogapi.payload.response;

public class ApiResponse<T> {
    private final int statusCodes;
    private final T data;
    private final String message;
    public ApiResponse(int statusCodes, String message, T data) {
        this.statusCodes = statusCodes;
        this.message = message;
        this.data = data;
    }

    public int getStatusCodes() {
        return this.statusCodes;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }
}
