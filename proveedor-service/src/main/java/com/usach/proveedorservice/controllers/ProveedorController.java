package com.usach.proveedorservice.controllers;

import com.usach.proveedorservice.entities.ProveedorEntity;
import com.usach.proveedorservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> getAllProveedores() {
        List<ProveedorEntity> proveedores = proveedorService.getAllproveedores();
        if(proveedores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProveedorEntity> getProveedorByCodigo(@PathVariable("codigo") String codigo) {
        ProveedorEntity proveedor = proveedorService.getProveedorByCodigo(codigo);
        if(proveedor == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> createProveedor(@RequestBody ProveedorEntity proveedor) {
        if(proveedorService.idAlreadyexists(proveedor.getCodigo_proveedor()))
            return ResponseEntity.badRequest().build();
        proveedorService.save(proveedor);
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable("codigo") String codigo) {
        if(!proveedorService.idAlreadyexists(codigo))
            return ResponseEntity.notFound().build();
        proveedorService.delete(codigo);
        return ResponseEntity.ok(null);
    }








}
