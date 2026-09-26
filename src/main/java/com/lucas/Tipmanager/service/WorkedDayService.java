package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
import com.lucas.Tipmanager.entity.Employee;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.entity.WorkedDay;
import com.lucas.Tipmanager.exception.EmployeeNotFoundException;
import com.lucas.Tipmanager.exception.InactiveEmployeeException;
import com.lucas.Tipmanager.exception.InvalidMonthException;
import com.lucas.Tipmanager.exception.WorkDayAlreadyClosedException;
import com.lucas.Tipmanager.exception.WorkDayNotFoundException;
import com.lucas.Tipmanager.exception.WorkedDayAlreadyExistsException;
import com.lucas.Tipmanager.repository.EmployeeRepository;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import com.lucas.Tipmanager.repository.WorkedDayRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
                .orElseThrow(() ->
                        new EmployeeNotFoundException(data.getEmployeeId())
                );

        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new InactiveEmployeeException(
                    data.getEmployeeId()
            );
        }

        WorkDay workDay = workDayRepository.findById(data.getWorkDayId())
                .orElseThrow(() ->
                        new WorkDayNotFoundException(data.getWorkDayId())
                );

        if (Boolean.TRUE.equals(workDay.getClosed())) {
            throw new WorkDayAlreadyClosedException(
                    data.getWorkDayId()
            );
        }

        boolean alreadyExists =
                workedDayRepository.existsByEmployeeIdAndWorkDayId(
                        data.getEmployeeId(),
                        data.getWorkDayId()
                );

        if (alreadyExists) {
            throw new WorkedDayAlreadyExistsException(
                    data.getEmployeeId(),
                    data.getWorkDayId()
            );
        }

        WorkedDay workedDay = new WorkedDay();

        workedDay.setEmployee(employee);
        workedDay.setWorkDay(workDay);

        return workedDayRepository.save(workedDay);
    }

    public List<WorkedDay> getAll() {
        return workedDayRepository.findAll();
    }

    public List<WorkedDay> getByWorkDay(Long workDayId) {
        return workedDayRepository.findByWorkDayId(workDayId);
    }

    public int countEmployeesByWorkDay(Long workDayId) {
        return workedDayRepository.findByWorkDayId(workDayId).size();
    }

    public BigDecimal getMonthlyTotal(
            Long employeeId,
            int year,
            int month
    ) {

        if (month < 1 || month > 12) {
            throw new InvalidMonthException(month);
        }

        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(employeeId)
                );

        LocalDate startDate = LocalDate.of(year, month, 1);

        LocalDate endDate = startDate
                .withDayOfMonth(startDate.lengthOfMonth());

        List<WorkedDay> workedDays =
                workedDayRepository.findByEmployeeIdAndWorkDay_DateBetween(
                        employeeId,
                        startDate,
                        endDate
                );

        return workedDays.stream()
                .map(WorkedDay::getAmountReceived)
                .filter(amount -> amount != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}