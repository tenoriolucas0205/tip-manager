package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
import com.lucas.Tipmanager.dto.WorkedDayResponseDTO;
import com.lucas.Tipmanager.entity.WorkedDay;
import com.lucas.Tipmanager.service.WorkedDayService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/worked-days")
public class WorkedDayController {

    private final WorkedDayService workedDayService;

    public WorkedDayController(WorkedDayService workedDayService) {
        this.workedDayService = workedDayService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkedDayResponseDTO create(
            @RequestBody WorkedDayRequestDTO data
    ) {
        WorkedDay workedDay = workedDayService.create(data);

        return new WorkedDayResponseDTO(workedDay);
    }

    @GetMapping
    public List<WorkedDayResponseDTO> getAll() {

        return workedDayService.getAll()
                .stream()
                .map(WorkedDayResponseDTO::new)
                .toList();
    }

    @GetMapping("/workday/{workDayId}")
    public List<WorkedDayResponseDTO> getByWorkDay(
            @PathVariable Long workDayId
    ) {

        return workedDayService.getByWorkDay(workDayId)
                .stream()
                .map(WorkedDayResponseDTO::new)
                .toList();
    }

    @GetMapping("/monthly/{employeeId}")
    public BigDecimal getMonthlyTotal(
            @PathVariable Long employeeId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return workedDayService.getMonthlyTotal(
                employeeId,
                year,
                month
        );
    }
}