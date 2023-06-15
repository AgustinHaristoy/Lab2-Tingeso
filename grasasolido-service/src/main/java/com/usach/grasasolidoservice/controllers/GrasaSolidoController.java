package com.usach.grasasolidoservice.controllers;

import com.usach.grasasolidoservice.entities.GrasaSolidoEntity;
import com.usach.grasasolidoservice.services.GrasaSolidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
@RequestMapping("/grasasolido")
public class GrasaSolidoController {
    @Autowired
    private GrasaSolidoService grasaSolidoService;

    @GetMapping
    public ResponseEntity<ArrayList<GrasaSolidoEntity>> getAllgrasasolido() {
        ArrayList<GrasaSolidoEntity> data = grasaSolidoService.getAllgrasasolido();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<GrasaSolidoEntity> getGrasaSolidoByProveedor(@PathVariable String proveedor) {
        GrasaSolidoEntity data = grasaSolidoService.findbyProveedor(proveedor);
        if (data == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public void guardarData(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws FileNotFoundException, ParseException {
        grasaSolidoService.guardar(file);
        grasaSolidoService.leerCsv("GrasaSolido.csv");
    }

}
