package com.usach.calculatorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcopioModel {
    private String fecha;
    private String turno;
    private String proveedor;
    private double kls_leche;
}
