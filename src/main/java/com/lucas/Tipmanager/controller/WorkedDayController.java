package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
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
    public WorkedDay create(@RequestBody WorkedDayRequestDTO data) {
        return workedDayService.create(data);
    }

    @GetMapping
    public List<WorkedDay> getAll() {
        return workedDayService.getAll();
    }

    @GetMapping("/workday/{workDayId}")
    public List<WorkedDay> getByWorkDay(@PathVariable Long workDayId) {
        return workedDayService.getByWorkDay(workDayId);
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