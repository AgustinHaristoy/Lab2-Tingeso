package com.usach.calculatorservice.controllers;

import com.usach.calculatorservice.entities.CalculatorEntity;
import com.usach.calculatorservice.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<List<CalculatorEntity>> getAllCalculators() {
        List<CalculatorEntity> data = calculatorService.getAllcalculator();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping("/{proveedor}")
    public void generarPagos(@PathVariable String proveedor) {
        calculatorService.generarPagos(proveedor);
    }
}
