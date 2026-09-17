package com.lucas.Tipmanager.service;

import com.lucas.Tipmanager.dto.WorkDayRequestDTO;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.repository.WorkDayRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WorkDayService {

    private final WorkDayRepository workDayRepository;

    public WorkDayService(WorkDayRepository workDayRepository) {
        this.workDayRepository = workDayRepository;
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
        } else {
            carryOverIn = BigDecimal.ZERO;
        }

        workDay.setCarryOverIn(carryOverIn);

        return workDayRepository.save(workDay);
    }

    public WorkDay close(Long id) {

        WorkDay workDay = workDayRepository.findById(id)
                .orElseThrow();

        return workDay;
    }
}