package com.example.demo.apiPayload.code;

import com.example.demo.apiPayload.code.ErrorReasonDTO;

public interface BaseErrorCode {

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
