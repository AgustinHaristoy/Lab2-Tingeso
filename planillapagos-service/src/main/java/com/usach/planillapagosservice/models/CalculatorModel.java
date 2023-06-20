package com.usach.planillapagosservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculatorModel {
    private String quincena;
    private double promedio_kls_leche;
    private double variacion_leche;
    private double variacion_grasa;
    private double variacion_st;
    private double pago_leche;
    private double pago_grasa;
    private double pago_st;
    private double bonificacion_frecuencia;
    private double descuento_leche;
    private double descuento_grasa;
    private double descuento_st;
    private double pago_total;
    private double monto_retencion;
    private double monto_final;
}
