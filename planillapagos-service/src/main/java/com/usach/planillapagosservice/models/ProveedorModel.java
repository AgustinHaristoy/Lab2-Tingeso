package com.usach.planillapagosservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProveedorModel {
    private String codigo_proveedor;
    private String nombre;
    private String categoria;
    private Boolean retencion;
}
