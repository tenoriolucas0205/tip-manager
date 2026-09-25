package com.lucas.Tipmanager.exception;

public class InactiveEmployeeException extends RuntimeException {

    public InactiveEmployeeException(Long id) {
        super("Employee " + id + " is inactive");
    }
}