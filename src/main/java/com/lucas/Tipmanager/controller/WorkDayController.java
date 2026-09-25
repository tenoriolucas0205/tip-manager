package com.lucas.Tipmanager.controller;

import com.lucas.Tipmanager.dto.WorkDayRequestDTO;
import com.lucas.Tipmanager.dto.WorkDayResponseDTO;
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
    public WorkDayResponseDTO create(
            @Valid @RequestBody WorkDayRequestDTO data
    ) {
        WorkDay workDay = workDayService.create(data);

        return new WorkDayResponseDTO(workDay);
    }

    @GetMapping
    public List<WorkDayResponseDTO> getAll() {

        return workDayService.getAll()
                .stream()
                .map(WorkDayResponseDTO::new)
                .toList();
    }

    @PostMapping("/{id}/close")
    public WorkDayResponseDTO close(@PathVariable Long id) {

        WorkDay workDay = workDayService.close(id);

        return new WorkDayResponseDTO(workDay);
    }
}