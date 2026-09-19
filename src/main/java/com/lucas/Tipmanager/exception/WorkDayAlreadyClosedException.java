package com.lucas.Tipmanager.exception;

public class WorkDayAlreadyClosedException extends RuntimeException {

    public WorkDayAlreadyClosedException(Long id) {
        super("WorkDay " + id + " is already closed");
    }
}