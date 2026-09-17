package com.lucas.Tipmanager.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class WorkedDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amountReceived;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private WorkDay workDay;

    public WorkedDay() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }
}