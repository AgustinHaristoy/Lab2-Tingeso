package com.usach.acopioservice.controllers;

import com.usach.acopioservice.entities.AcopioEntity;
import com.usach.acopioservice.services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/acopio")
public class AcopioController {
    @Autowired
    private AcopioService acopioService;

    @GetMapping
    public ResponseEntity<List<AcopioEntity>> getAllacopio() {
        List<AcopioEntity> data = acopioService.getAllacopio();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }
    @PostMapping
    public void guardarData(@RequestParam("file") MultipartFile file) {
        acopioService.guardar(file);
        acopioService.leerCsv("Acopio.csv");
    }
}
