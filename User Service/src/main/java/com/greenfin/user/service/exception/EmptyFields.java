package com.greenfin.user.service.exception;

public class EmptyFields extends GlobalException{
    public EmptyFields(String message, String errorCode) {
        super(message, errorCode);
    }
}
