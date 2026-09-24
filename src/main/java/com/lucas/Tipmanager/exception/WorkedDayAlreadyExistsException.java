package com.lucas.Tipmanager.exception;

public class WorkedDayAlreadyExistsException extends RuntimeException {

    public WorkedDayAlreadyExistsException(Long employeeId, Long workDayId) {
        super(
                "Employee " + employeeId
                        + " is already registered for WorkDay " + workDayId
        );
    }
}