package com.usach.calculatorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrasaSolidoModel {
    private String proveedor;
    private double grasa;
    private double solido;
}
