package com.lucas.Tipmanager.dto;

import com.lucas.Tipmanager.entity.Employee;

import java.time.LocalDateTime;

public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private Boolean active;
    private LocalDateTime createdAt;

    public EmployeeResponseDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.active = employee.getActive();
        this.createdAt = employee.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}