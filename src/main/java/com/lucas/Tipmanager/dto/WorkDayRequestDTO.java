package com.lucas.Tipmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkDayRequestDTO {

    @NotNull
    private LocalDate date;

    @NotNull
    @PositiveOrZero
    private BigDecimal totalTip;

    public WorkDayRequestDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalTip() {
        return totalTip;
    }

    public void setTotalTip(BigDecimal totalTip) {
        this.totalTip = totalTip;
    }
}