package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class GroupException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public GroupException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
