package com.lucas.Tipmanager.repository;

import com.lucas.Tipmanager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}