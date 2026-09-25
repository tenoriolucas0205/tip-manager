package com.lucas.Tipmanager.exception;

public class WorkDayAlreadyExistsException extends RuntimeException {

    public WorkDayAlreadyExistsException(String date) {
        super("WorkDay for date " + date + " already exists");
    }
}