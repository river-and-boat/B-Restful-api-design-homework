package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class StudentException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public StudentException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
