package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkedDayRequestDTO;
import com.lucas.Tipmanager.entity.WorkedDay;
import com.lucas.Tipmanager.service.WorkedDayService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}