package io.xor.project.blogapi.payload.response;


import lombok.Getter;

@Getter
public class ReadApiResponse<T> extends  ApiResponse {
    private int page;
    private int limit;
    private boolean isLast;
    private int total;

    public ReadApiResponse(int statusCodes, String message, T data, int total, boolean isLast) {
        super(statusCodes, message, data);
        this.page = 0;
        this.limit = 10;
        this.isLast = isLast;
        this.total = total;
    }

    public ReadApiResponse(int page, int limit, int total, boolean isLast, int statusCodes, String message, T data) {
        super(statusCodes, message, data);
        this.page = page;
        this.limit = limit;
        this.isLast = isLast;
        this.total = total;
    }



}
