package com.usach.grasasolidoservice.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RegistroModel {
    private String proveedor;
    private String fecha;
    private String turno;
    private Double kls_leche;
    private Double grasa;
    private Double solido;
}
