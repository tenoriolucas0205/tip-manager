package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.EmployeeRequestDTO;
import com.lucas.Tipmanager.dto.EmployeeResponseDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public EmployeeResponseDTO create(
            @Valid @RequestBody EmployeeRequestDTO data
    ) {
        Employee employee = employeeService.create(data);

        return new EmployeeResponseDTO(employee);
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {

        return employeeService.getAll()
                .stream()
                .map(EmployeeResponseDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getById(
            @PathVariable Long id
    ) {
        Employee employee = employeeService.getById(id);

        return new EmployeeResponseDTO(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO data
    ) {
        Employee employee = employeeService.update(id, data);

        return new EmployeeResponseDTO(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @PatchMapping("/{id}/status")
    public EmployeeResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean active
    ) {
        Employee employee =
                employeeService.updateStatus(id, active);

        return new EmployeeResponseDTO(employee);
    }
}