package com.lucas.Tipmanager.exception;

public class NoEmployeesWorkedException extends RuntimeException {

    public NoEmployeesWorkedException(Long workDayId) {
        super("WorkDay " + workDayId + " has no employees registered");
    }
}