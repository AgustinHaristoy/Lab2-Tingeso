package com.usach.quincenaservice.controllers;

import com.usach.quincenaservice.entities.QuincenaEntity;
import com.usach.quincenaservice.services.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quincena")
public class QuincenaController {
    @Autowired
    private QuincenaService quincenaService;

    @GetMapping
    public ResponseEntity<List<QuincenaEntity>> getAllquincenas() {
        List<QuincenaEntity> data = quincenaService.getAllQuincenas();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<List<QuincenaEntity>> getQuincenaByProveedor(@PathVariable String proveedor) {
        List<QuincenaEntity> data = quincenaService.getQuincenasByProveedor(proveedor);
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping("/{fecha}/{proveedor}")
    public void crearQuincena(@PathVariable String fecha, @PathVariable String proveedor) {
        quincenaService.crearQuincena(fecha, proveedor);
    }



}
