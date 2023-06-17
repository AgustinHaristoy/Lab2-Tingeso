package com.usach.calculatorservice.repositories;

import com.usach.calculatorservice.entities.CalculatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<CalculatorEntity, Long> {
}
