package com.usach.calculatorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuincenaModel {
    private Long id;
    private String proveedor;
    private String fecha;
    private Integer quincena;
    private double kls_leche;
    private Double grasa;
    private Double solido;
    private Integer dias;
    private Integer bonificacion;
    private boolean actual;
    private boolean anterior;
}
