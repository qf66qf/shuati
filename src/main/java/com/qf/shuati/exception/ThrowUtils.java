package com.qf.shuati.exception;

import com.qf.shuati.common.ErrorCode;

public class ThrowUtils {

    /**
     * 条件成立则立即抛异常
     * @param condition
     * @param e
     */
    public static void throwIf(boolean condition, RuntimeException e) {
        if(condition){
            throw e;
        }
    }

    public static void throwIf(boolean condition, ErrorCode e) {
        throwIf(condition, new BusinessException(e));
    }

    public static void throwIf(boolean condition,ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode,message));
    }
}
