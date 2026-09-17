package com.lucas.Tipmanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkDayRequestDTO {

    private LocalDate date;

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