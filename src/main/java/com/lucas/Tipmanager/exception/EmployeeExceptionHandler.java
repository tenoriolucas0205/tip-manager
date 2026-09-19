package com.lucas.Tipmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFound(EmployeeNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
    }

    @ExceptionHandler(WorkDayAlreadyClosedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleWorkDayAlreadyClosed(
            WorkDayAlreadyClosedException exception
    ) {
        return exception.getMessage();
    }
}