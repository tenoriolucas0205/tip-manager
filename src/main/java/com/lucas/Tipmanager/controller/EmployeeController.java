package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.EmployeeRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeRequestDTO data) {
        return employeeService.create(data);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        employeeService.delete(id);

    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO data
    ) {
        return employeeService.update(id, data);
    }

    @PatchMapping("/{id}/status")
    public Employee updateStatus(@PathVariable Long id,@RequestParam Boolean active){

        return employeeService.updateStatus(id,active);
    }
}