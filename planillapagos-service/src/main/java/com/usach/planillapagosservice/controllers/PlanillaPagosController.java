package com.usach.planillapagosservice.controllers;

import com.usach.planillapagosservice.entities.PlanillaPagosEntity;
import com.usach.planillapagosservice.services.PlanillaPagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planillapagos")
public class PlanillaPagosController {
    @Autowired
    private PlanillaPagosService planillaPagosService;

    @GetMapping
    public ResponseEntity<List<PlanillaPagosEntity>> getAllPlanillaPagos(){
        List<PlanillaPagosEntity> planillas = planillaPagosService.getAllPlanillaPagos();
        if(planillas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planillas);
    }

    @PostMapping
    public void crearPlanilla(@RequestParam("proveedor") String proveedor){
        planillaPagosService.crearPlanilla(proveedor);
    }




}
