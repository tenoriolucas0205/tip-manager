package com.lucas.Tipmanager.exception;

public class WorkDayNotFoundException extends RuntimeException {

    public WorkDayNotFoundException(Long id) {
        super("WorkDay with id " + id + " not found");
    }
}