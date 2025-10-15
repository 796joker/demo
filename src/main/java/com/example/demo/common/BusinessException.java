package com.example.demo.common;

/*
    * Custom exception class for business logic errors.
    * use for throwing exceptions related to business rules and validations.
    * This class can be extended to include additional information such as error codes or user-friendly messages
 */
public class BusinessException extends RuntimeException {
    private final int code;
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
