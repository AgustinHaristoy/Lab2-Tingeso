package com.usach.registroservice.controllers;

import com.usach.registroservice.entities.RegistroEntity;
import com.usach.registroservice.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<RegistroEntity>> getAllregistro() {
        List<RegistroEntity> data = registroService.getAllregistro();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/quincena/{fecha}")
    public ResponseEntity<List<RegistroEntity>> getRegistroByQuincena(@PathVariable String fecha) {
        List<RegistroEntity> data = registroService.getRegistroByQuincena(fecha);
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/kls_totales/{proveedor}/{fecha}")
    public double getKlsTotales(@PathVariable String proveedor, @PathVariable String fecha) {
        return registroService.getKlsTotales(proveedor, fecha);
    }

    @GetMapping("/grasa/{proveedor}/{fecha}")
    public double getGrasa(@PathVariable String proveedor, @PathVariable String fecha) {
        return registroService.getGrasaByProveedor(proveedor, fecha);
    }

    @GetMapping("/solido/{proveedor}/{fecha}")
    public double getSolido(@PathVariable String proveedor, @PathVariable String fecha) {
        return registroService.getSolidoByProveedor(proveedor, fecha);
    }

    @GetMapping("/dias/{proveedor}/{fecha}")
    public int getDias(@PathVariable String proveedor, @PathVariable String fecha) {
        return registroService.getDiasTotales(proveedor, fecha);
    }

    @GetMapping("/bonificacion/{proveedor}/{fecha}")
    public int getBonificacion(@PathVariable String proveedor, @PathVariable String fecha) {
        return registroService.getBonificacion(proveedor, fecha);
    }


    @PostMapping("/acopio")
    public void guardarAcopio(String proveedor, String fecha, String turno, Double kls_leche){
        registroService.guardarAcopio(proveedor, fecha, turno, kls_leche);
    }

    @PostMapping("/grasasolido")
    public void guardarGrasaSolido(String proveedor, Double grasa, Double solido){
        registroService.guardarGrasaSolido(proveedor, grasa, solido);
    }

    @DeleteMapping
    public void deleteAll(){
        registroService.eliminarRegistro();
    }


}
