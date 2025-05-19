package com.example.demo.apiPayload.exception.handler;

import com.example.demo.apiPayload.code.status.ErrorStatus;
import com.example.demo.apiPayload.exception.GeneralException;

/**
 * foodCategory 조회 실패 시 던질 커스텀 런타임 예외
 */
public class FoodCategoryHandler extends RuntimeException {

    private final ErrorStatus errorStatus;

    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }

    public String getErrorCode() {
        // → errorStatus.code()  X
        return errorStatus.getCode();
    }
}

