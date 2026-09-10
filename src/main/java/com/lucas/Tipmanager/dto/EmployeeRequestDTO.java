package com.lucas.Tipmanager.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDTO {

    @NotBlank
    private String name;

    public EmployeeRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}