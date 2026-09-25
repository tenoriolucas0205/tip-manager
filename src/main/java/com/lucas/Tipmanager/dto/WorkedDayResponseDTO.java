package com.lucas.Tipmanager.dto;

import com.lucas.Tipmanager.entity.WorkedDay;

import java.math.BigDecimal;

public class WorkedDayResponseDTO {

    private Long id;
    private Long employeeId;
    private Long workDayId;
    private BigDecimal amountReceived;

    public WorkedDayResponseDTO(WorkedDay workedDay) {
        this.id = workedDay.getId();
        this.employeeId = workedDay.getEmployee().getId();
        this.workDayId = workedDay.getWorkDay().getId();
        this.amountReceived = workedDay.getAmountReceived();
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getWorkDayId() {
        return workDayId;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }
}