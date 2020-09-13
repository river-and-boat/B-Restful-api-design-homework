package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public enum ExceptionEnum {

    SAVE_STUDENT_EXCEPTION(10001, "save student fail"),
    DELETE_STUDENT_NOT_EXIST(10002, "delete student not exist"),
    GET_STUDENT_NOT_EXIST(10003, "student not exist"),
    UPDATE_STUDENT_NOT_EXIST(10004, "can not find a student to update");

    private Integer code;
    private String errorMessage;

    ExceptionEnum(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
