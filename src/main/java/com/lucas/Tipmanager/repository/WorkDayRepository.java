package com.lucas.Tipmanager.repository;

import com.lucas.Tipmanager.entity.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findTopByOrderByDateDesc();

    boolean existsByDate(java.time.LocalDate date);
}