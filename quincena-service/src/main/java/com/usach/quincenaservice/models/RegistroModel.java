package com.usach.quincenaservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroModel {
    private String proveedor;
    private String fecha;
    private String turno;
    private Double kls_leche;
    private Double grasa;
    private Double solido;
}
