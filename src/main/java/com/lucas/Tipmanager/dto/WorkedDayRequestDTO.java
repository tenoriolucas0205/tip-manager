package com.lucas.Tipmanager.dto;

public class WorkedDayRequestDTO {

    private Long employeeId;

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