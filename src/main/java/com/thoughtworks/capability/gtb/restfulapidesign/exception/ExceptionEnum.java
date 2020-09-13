package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public enum ExceptionEnum {

    SAVE_STUDENT_EXCEPTION(10001, "save student fail"),
    DELETE_STUDENT_NOT_EXIST(10002, "delete student not exist"),
    GET_STUDENT_NOT_EXIST(10003, "student not exist"),
    UPDATE_STUDENT_NOT_EXIST(10004, "can not find a student to update"),
    REQUEST_PARAMETER_NOT_MATCH(10005, "the parameters of post data are not match the rule"),

    UPDATE_GROUP_NAME_NOT_EXIST(10006, "can not find a team to update"),
    SAVE_GROUP_WITH_ILLEGAL_NAME(10007, "the team name is illegal");

    private Integer code;
    private String errorMessage;

    ExceptionEnum(Integer code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Integer getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
