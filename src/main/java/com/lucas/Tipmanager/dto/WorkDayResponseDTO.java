package com.lucas.Tipmanager.dto;

import com.lucas.Tipmanager.entity.WorkDay;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkDayResponseDTO {

    private Long id;
    private LocalDate date;
    private BigDecimal totalTip;
    private BigDecimal carryOverIn;
    private BigDecimal kitchenAmount;
    private BigDecimal carryOverOut;
    private Boolean closed;

    public WorkDayResponseDTO(WorkDay workDay) {
        this.id = workDay.getId();
        this.date = workDay.getDate();
        this.totalTip = workDay.getTotalTip();
        this.carryOverIn = workDay.getCarryOverIn();
        this.kitchenAmount = workDay.getKitchenAmount();
        this.carryOverOut = workDay.getCarryOverOut();
        this.closed = workDay.getClosed();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotalTip() {
        return totalTip;
    }

    public BigDecimal getCarryOverIn() {
        return carryOverIn;
    }

    public BigDecimal getKitchenAmount() {
        return kitchenAmount;
    }

    public BigDecimal getCarryOverOut() {
        return carryOverOut;
    }

    public Boolean getClosed() {
        return closed;
    }
}