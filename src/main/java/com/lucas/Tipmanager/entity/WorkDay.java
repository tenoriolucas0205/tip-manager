package com.lucas.Tipmanager.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private BigDecimal totalTip;

    private BigDecimal carryOverIn;

    private BigDecimal kitchenAmount;

    private BigDecimal carryOverOut;

    private Boolean closed = false;

    public WorkDay() {
    }

    public Long getId() {
        return id;
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

    public BigDecimal getCarryOverIn() {
        return carryOverIn;
    }

    public void setCarryOverIn(BigDecimal carryOverIn) {
        this.carryOverIn = carryOverIn;
    }

    public BigDecimal getKitchenAmount() {
        return kitchenAmount;
    }

    public void setKitchenAmount(BigDecimal kitchenAmount) {
        this.kitchenAmount = kitchenAmount;
    }

    public BigDecimal getCarryOverOut() {
        return carryOverOut;
    }

    public void setCarryOverOut(BigDecimal carryOverOut) {
        this.carryOverOut = carryOverOut;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}