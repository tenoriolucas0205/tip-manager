package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkDayRequestDTO;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.service.WorkDayService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workdays")
public class WorkDayController {

    private final WorkDayService workDayService;

    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkDay create(@RequestBody WorkDayRequestDTO data) {
        return workDayService.create(data);
    }

    @PostMapping("/{id}/close")
    public WorkDay close(@PathVariable Long id) {
        return workDayService.close(id);
    }
}