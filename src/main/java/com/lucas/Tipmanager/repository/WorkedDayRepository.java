package com.lucas.Tipmanager.repository;

import com.lucas.Tipmanager.entity.WorkedDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkedDayRepository extends JpaRepository<WorkedDay, Long> {

    List<WorkedDay> findByWorkDayId(Long workDayId);

    boolean existsByEmployeeIdAndWorkDayId(
            Long employeeId,
            Long workDayId
    );
}