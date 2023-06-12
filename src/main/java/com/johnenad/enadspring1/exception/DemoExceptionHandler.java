package com.johnenad.enadspring1.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@Slf4j
@ControllerAdvice
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public Errors internalServerException(InternalServerException ex) {
        log.error("Internal Server Exception: {}", ex.getMessage());
        return buildError(new ErrorResponse(ex.getCode(), ex.getMessage(), ex.getDetails()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConstraintException.class)
    public Errors constraintException(ConstraintException ex) {
        log.error("Constraint Exception: {}", ex.getMessage());
        return buildError(new ErrorResponse(ex.getCode(), ex.getMessage(), ex.getDetails()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Errors badRequestException(BadRequestException ex) {
        log.error("Bad Request Exception: {}", ex.getMessage());
        return buildError(new ErrorResponse(ex.getCode(), ex.getMessage(), ex.getDetails()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public Errors missingRequestHeaderException(MissingRequestHeaderException ex) {
        log.error("Missing Request Header Exception: {}", ex.getMessage());
        return buildError(new ErrorResponse("Bad Request", ex.getMessage(), "Missing Request Header"));
    }

    private Errors buildError(ErrorResponse errorResponse) {
        Errors errors = new Errors();
        List<ErrorResponse> errorList = Arrays.asList(errorResponse);
        errors.setErrorResponses(errorList);
        return errors;

    }
}