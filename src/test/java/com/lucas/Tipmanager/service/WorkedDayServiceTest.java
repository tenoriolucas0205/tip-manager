package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.exception.EmployeeNotFoundException;
import com.lucas.Tipmanager.exception.InactiveEmployeeException;
import com.lucas.Tipmanager.exception.WorkDayAlreadyClosedException;
import com.lucas.Tipmanager.repository.EmployeeRepository;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import com.lucas.Tipmanager.repository.WorkedDayRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.lucas.Tipmanager.exception.WorkedDayAlreadyExistsException;
import com.lucas.Tipmanager.entity.WorkDay;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkedDayServiceTest {

    @Mock
    private WorkedDayRepository workedDayRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkDayRepository workDayRepository;

    @InjectMocks
    private WorkedDayService workedDayService;

    @Test
    void shouldNotRegisterInactiveEmployee() {

        Long employeeId = 1L;
        Long workDayId = 1L;

        Employee employee = new Employee("Lucas");
        employee.setActive(false);

        WorkedDayRequestDTO data = new WorkedDayRequestDTO();
        data.setEmployeeId(employeeId);
        data.setWorkDayId(workDayId);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        assertThrows(
                InactiveEmployeeException.class,
                () -> workedDayService.create(data)
        );

        verify(employeeRepository).findById(employeeId);
        verify(workDayRepository, never()).findById(workDayId);
        verify(workedDayRepository, never()).save(any());
    }

    @Test
    void shouldNotRegisterEmployeeTwiceInSameWorkDay() {

        Long employeeId = 1L;
        Long workDayId = 1L;

        Employee employee = new Employee("Lucas");

        WorkDay workDay = new WorkDay();

        WorkedDayRequestDTO data = new WorkedDayRequestDTO();
        data.setEmployeeId(employeeId);
        data.setWorkDayId(workDayId);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(workDayRepository.findById(workDayId))
                .thenReturn(Optional.of(workDay));

        when(workedDayRepository.existsByEmployeeIdAndWorkDayId(
                employeeId,
                workDayId
        )).thenReturn(true);

        assertThrows(
                WorkedDayAlreadyExistsException.class,
                () -> workedDayService.create(data)
        );

        verify(workedDayRepository)
                .existsByEmployeeIdAndWorkDayId(
                        employeeId,
                        workDayId
                );

        verify(workedDayRepository, never()).save(any());
    }
    @Test
    void shouldNotRegisterEmployeeInClosedWorkDay() {

        Long employeeId = 1L;
        Long workDayId = 1L;

        Employee employee = new Employee("Lucas");

        WorkDay workDay = new WorkDay();
        workDay.setClosed(true);

        WorkedDayRequestDTO data = new WorkedDayRequestDTO();
        data.setEmployeeId(employeeId);
        data.setWorkDayId(workDayId);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employee));

        when(workDayRepository.findById(workDayId))
                .thenReturn(Optional.of(workDay));

        assertThrows(
                WorkDayAlreadyClosedException.class,
                () -> workedDayService.create(data)
        );

        verify(workDayRepository).findById(workDayId);

        verify(workedDayRepository, never())
                .existsByEmployeeIdAndWorkDayId(anyLong(), anyLong());

        verify(workedDayRepository, never())
                .save(any());
    }
    @Test
    void shouldNotRegisterNonExistingEmployee() {

        Long employeeId = 999L;
        Long workDayId = 1L;

        WorkedDayRequestDTO data = new WorkedDayRequestDTO();
        data.setEmployeeId(employeeId);
        data.setWorkDayId(workDayId);

        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> workedDayService.create(data)
        );

        verify(employeeRepository).findById(employeeId);

        verify(workDayRepository, never())
                .findById(workDayId);

        verify(workedDayRepository, never())
                .save(any());
    }

}