package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.WorkDayRequestDTO;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.entity.WorkedDay;
import com.lucas.Tipmanager.exception.WorkDayAlreadyClosedException;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import com.lucas.Tipmanager.repository.WorkedDayRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayService {

    private final WorkDayRepository workDayRepository;
    private final WorkedDayRepository workedDayRepository;

    public WorkDayService(
            WorkDayRepository workDayRepository,
            WorkedDayRepository workedDayRepository
    ) {
        this.workDayRepository = workDayRepository;
        this.workedDayRepository = workedDayRepository;
    }

    public WorkDay create(WorkDayRequestDTO data) {

        WorkDay workDay = new WorkDay();

        workDay.setDate(data.getDate());
        workDay.setTotalTip(data.getTotalTip());

        Optional<WorkDay> lastWorkDay =
                workDayRepository.findTopByOrderByDateDesc();

        BigDecimal carryOverIn;

        if (lastWorkDay.isPresent()) {
            carryOverIn = lastWorkDay.get().getCarryOverOut();

            if (carryOverIn == null) {
                carryOverIn = BigDecimal.ZERO;
            }
        } else {
            carryOverIn = BigDecimal.ZERO;
        }

        workDay.setCarryOverIn(carryOverIn);

        return workDayRepository.save(workDay);
    }

    public WorkDay close(Long id) {

        WorkDay workDay = workDayRepository.findById(id)
                .orElseThrow();

        if (Boolean.TRUE.equals(workDay.getClosed())) {
            throw new WorkDayAlreadyClosedException(id);
        }

        List<WorkedDay> workedDays =
                workedDayRepository.findByWorkDayId(id);

        int employeeCount = workedDays.size();

        int totalShares = employeeCount + 2;

        BigDecimal availableAmount =
                workDay.getTotalTip().add(workDay.getCarryOverIn());

        BigDecimal rawShare = availableAmount
                .divide(
                        BigDecimal.valueOf(totalShares),
                        10,
                        RoundingMode.DOWN
                );

        BigDecimal share = rawShare
                .divide(new BigDecimal("0.10"), 0, RoundingMode.DOWN)
                .multiply(new BigDecimal("0.10"));

        for (WorkedDay workedDay : workedDays) {
            workedDay.setAmountReceived(share);
        }

        workedDayRepository.saveAll(workedDays);

        BigDecimal employeesAmount =
                share.multiply(BigDecimal.valueOf(employeeCount));

        BigDecimal kitchenAmount =
                share.multiply(BigDecimal.valueOf(2));

        BigDecimal distributedAmount =
                employeesAmount.add(kitchenAmount);

        BigDecimal carryOverOut =
                availableAmount.subtract(distributedAmount);

        workDay.setKitchenAmount(kitchenAmount);
        workDay.setCarryOverOut(carryOverOut);
        workDay.setClosed(true);

        return workDayRepository.save(workDay);
    }
}