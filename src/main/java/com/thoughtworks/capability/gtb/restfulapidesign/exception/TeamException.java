package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class TeamException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public TeamException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
