package com.thoughtworks.capability.gtb.restfulapidesign.handler;

import com.thoughtworks.capability.gtb.restfulapidesign.common.ErrorResult;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.TeamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GtbExceptionHandler {
    @ExceptionHandler(StudentException.class)
    public ResponseEntity studentException(StudentException studentException) {
        ErrorResult errorResult = new ErrorResult(studentException.getExceptionEnum().getCode(),
                studentException.getExceptionEnum().getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(TeamException.class)
    public ResponseEntity teamException(TeamException teamException) {
        ErrorResult errorResult = new ErrorResult(teamException.getExceptionEnum().getCode(),
                teamException.getExceptionEnum().getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintException(ConstraintViolationException ex) {
        ErrorResult errorResult = new ErrorResult(ExceptionEnum.REQUEST_PARAMETER_NOT_MATCH.getCode(),
                ExceptionEnum.REQUEST_PARAMETER_NOT_MATCH.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        ErrorResult errorResult = new ErrorResult(ExceptionEnum.REQUEST_PARAMETER_NOT_MATCH.getCode(),
                ExceptionEnum.REQUEST_PARAMETER_NOT_MATCH.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResult> requestParameterException(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        String message = parameterName + "是必填项";
        ErrorResult errorResult = new ErrorResult(ExceptionEnum.REQUEST_PARAMETER_NOT_MATCH.getCode(), message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
