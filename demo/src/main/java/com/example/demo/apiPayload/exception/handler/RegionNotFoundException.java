package com.example.demo.apiPayload.exception.handler;

import com.example.demo.apiPayload.code.status.ErrorStatus;

public class RegionNotFoundException extends RuntimeException {
    private final ErrorStatus status;
    public RegionNotFoundException(ErrorStatus status){
        super(status.getMessage());
        this.status = status;
    }
    public ErrorStatus getErrorStatus(){ return status; }
}
