package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.EmployeeRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(EmployeeRequestDTO data) {
        Employee employee = new Employee(data.getName());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}