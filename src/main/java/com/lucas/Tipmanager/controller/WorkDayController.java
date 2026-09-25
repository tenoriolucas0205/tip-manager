package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkDayRequestDTO;
import com.lucas.Tipmanager.entity.WorkDay;
import com.lucas.Tipmanager.service.WorkDayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workdays")
public class WorkDayController {

    private final WorkDayService workDayService;

    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkDay create(
            @Valid @RequestBody WorkDayRequestDTO data
    ) {
        return workDayService.create(data);
    }

    @GetMapping
    public List<WorkDay> getAll() {
        return workDayService.getAll();
    }

    @PostMapping("/{id}/close")
    public WorkDay close(@PathVariable Long id) {
        return workDayService.close(id);
    }
}