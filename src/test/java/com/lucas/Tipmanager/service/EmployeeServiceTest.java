package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.EmployeeRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.lucas.Tipmanager.exception.EmployeeNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void shouldCreateEmployee() {

        EmployeeRequestDTO data = new EmployeeRequestDTO();
        data.setName("Lucas");

        Employee employee = new Employee("Lucas");

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        Employee result = employeeService.create(data);

        assertEquals("Lucas", result.getName());
        assertTrue(result.getActive());

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {

        Long id = 999L;

        when(employeeRepository.findById(id))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getById(id)
        );

        verify(employeeRepository).findById(id);
    }

    @Test
    void shouldUpdateEmployeeName() {

        Long id = 1L;

        Employee employee = new Employee("Lucas");

        EmployeeRequestDTO data = new EmployeeRequestDTO();
        data.setName("João");

        when(employeeRepository.findById(id))
                .thenReturn(java.util.Optional.of(employee));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result = employeeService.update(id, data);

        assertEquals("João", result.getName());

        verify(employeeRepository).findById(id);
        verify(employeeRepository).save(employee);
    }

    @Test
    void shouldUpdateEmployeeStatus() {

        Long id = 1L;

        Employee employee = new Employee("Lucas");

        when(employeeRepository.findById(id))
                .thenReturn(java.util.Optional.of(employee));

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result = employeeService.updateStatus(id, false);

        assertFalse(result.getActive());

        verify(employeeRepository).findById(id);
        verify(employeeRepository).save(employee);
    }
}