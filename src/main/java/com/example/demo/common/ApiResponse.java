package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Standard API response wrapper.
 *
 * @param <T> the type of the response data
 */
@Setter
@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public ApiResponse() {}

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public static <T> ApiResponse<T> ok(T data) { return new ApiResponse<>(0, "OK", data); }
    public static <T> ApiResponse<T> ok()       { return new ApiResponse<>(0, "OK", null); }
    public static <T> ApiResponse<T> fail(int code, String message) { return new ApiResponse<>(code, message, null); }
}
