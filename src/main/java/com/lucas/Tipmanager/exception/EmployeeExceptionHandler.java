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
    public ErrorResponse handleEmployeeNotFound(
            EmployeeNotFoundException exception
    ) {
        return new ErrorResponse(
                404,
                exception.getMessage()
        );
    }

    @ExceptionHandler(WorkDayNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleWorkDayNotFound(
            WorkDayNotFoundException exception
    ) {
        return new ErrorResponse(
                404,
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        return new ErrorResponse(
                400,
                exception.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage()
        );
    }

    @ExceptionHandler(WorkDayAlreadyClosedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleWorkDayAlreadyClosed(
            WorkDayAlreadyClosedException exception
    ) {
        return new ErrorResponse(
                409,
                exception.getMessage()
        );
    }

    @ExceptionHandler(WorkedDayAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleWorkedDayAlreadyExists(
            WorkedDayAlreadyExistsException exception
    ) {
        return new ErrorResponse(
                409,
                exception.getMessage()
        );
    }

    @ExceptionHandler(WorkDayAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleWorkDayAlreadyExists(
            WorkDayAlreadyExistsException exception
    ) {
        return new ErrorResponse(
                409,
                exception.getMessage()
        );
    }

    @ExceptionHandler(InactiveEmployeeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleInactiveEmployee(
            InactiveEmployeeException exception
    ) {
        return new ErrorResponse(
                409,
                exception.getMessage()
        );
    }

    @ExceptionHandler(NoEmployeesWorkedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleNoEmployeesWorked(
            NoEmployeesWorkedException exception
    ) {
        return new ErrorResponse(
                409,
                exception.getMessage()
        );
    }
}