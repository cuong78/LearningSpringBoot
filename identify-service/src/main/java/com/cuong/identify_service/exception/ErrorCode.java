package com.cuong.identify_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized Exception"),

    INVALID_KEY(1001,"Invalid Key"),

    USER_EXSITTED(1002,"User was exsitted"),

    USERNAME_INVALID(1003,"Username must be at least 3 characters"),

    PASSWORD_INVALID(1004,"Password must be at least 8 characters"),


    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
