package com.qf.shuati.exception;

import com.qf.shuati.common.ErrorCode;

public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {return code;}
}
