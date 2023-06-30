package com.usach.planillapagosservice.controllers;

import com.usach.planillapagosservice.entities.PlanillaPagosEntity;
import com.usach.planillapagosservice.services.PlanillaPagosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planillapagos")
public class PlanillaPagosController {
    @Autowired
    private PlanillaPagosService planillaPagosService;

    Logger logger = LoggerFactory.getLogger(PlanillaPagosController.class);

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
        logger.info("Creando planilla para proveedor: " + proveedor);
        planillaPagosService.crearPlanilla(proveedor);
    }




}
