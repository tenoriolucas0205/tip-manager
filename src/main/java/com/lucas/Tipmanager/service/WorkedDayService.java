package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.entity.WorkedDay;
import com.lucas.Tipmanager.repository.EmployeeRepository;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import com.lucas.Tipmanager.repository.WorkedDayRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkedDayService {

    private final WorkedDayRepository workedDayRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkDayRepository workDayRepository;

    public WorkedDayService(
            WorkedDayRepository workedDayRepository,
            EmployeeRepository employeeRepository,
            WorkDayRepository workDayRepository
    ) {
        this.workedDayRepository = workedDayRepository;
        this.employeeRepository = employeeRepository;
        this.workDayRepository = workDayRepository;
    }

    public WorkedDay create(WorkedDayRequestDTO data) {

        Employee employee = employeeRepository.findById(data.getEmployeeId())
                .orElseThrow();

        WorkDay workDay = workDayRepository.findById(data.getWorkDayId())
                .orElseThrow();

        WorkedDay workedDay = new WorkedDay();

        workedDay.setEmployee(employee);
        workedDay.setWorkDay(workDay);

        return workedDayRepository.save(workedDay);
    }

    public int countEmployeesByWorkDay(Long workDayId) {

        return workedDayRepository.findByWorkDayId(workDayId).size();
    }
}