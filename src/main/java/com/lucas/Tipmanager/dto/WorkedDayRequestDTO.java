package com.lucas.Tipmanager.dto;

import jakarta.validation.constraints.NotNull;

public class WorkedDayRequestDTO {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long workDayId;

    public WorkedDayRequestDTO() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getWorkDayId() {
        return workDayId;
    }

    public void setWorkDayId(Long workDayId) {
        this.workDayId = workDayId;
    }
}