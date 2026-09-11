package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.EmployeeRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.exception.EmployeeNotFoundException;
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
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(long id,EmployeeRequestDTO data){
        Employee employee = getById (id);

        employee.setName(data.getName());

        return employeeRepository.save(employee);
    }

    public Employee updateStatus(Long id,Boolean active){
        Employee employee = getById(id);

        employee.setActive(active);

        return employeeRepository.save(employee);
    }
}