package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}