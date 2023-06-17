package com.usach.calculatorservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="calculator")
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorEntity {
    @Id
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuincena() {
        return quincena;
    }

    public void setQuincena(String quincena) {
        this.quincena = quincena;
    }

    public double getPromedio_kls_leche() {
        return promedio_kls_leche;
    }

    public void setPromedio_kls_leche(double promedio_kls_leche) {
        this.promedio_kls_leche = promedio_kls_leche;
    }

    public double getVariacion_leche() {
        return variacion_leche;
    }

    public void setVariacion_leche(double variacion_leche) {
        this.variacion_leche = variacion_leche;
    }

    public double getVariacion_grasa() {
        return variacion_grasa;
    }

    public void setVariacion_grasa(double variacion_grasa) {
        this.variacion_grasa = variacion_grasa;
    }

    public double getVariacion_st() {
        return variacion_st;
    }

    public void setVariacion_st(double variacion_st) {
        this.variacion_st = variacion_st;
    }

    public double getPago_leche() {
        return pago_leche;
    }

    public void setPago_leche(double pago_leche) {
        this.pago_leche = pago_leche;
    }

    public double getPago_grasa() {
        return pago_grasa;
    }

    public void setPago_grasa(double pago_grasa) {
        this.pago_grasa = pago_grasa;
    }

    public double getPago_st() {
        return pago_st;
    }

    public void setPago_st(double pago_st) {
        this.pago_st = pago_st;
    }

    public double getBonificacion_frecuencia() {
        return bonificacion_frecuencia;
    }

    public void setBonificacion_frecuencia(double bonificacion_frecuencia) {
        this.bonificacion_frecuencia = bonificacion_frecuencia;
    }

    public double getDescuento_leche() {
        return descuento_leche;
    }

    public void setDescuento_leche(double descuento_leche) {
        this.descuento_leche = descuento_leche;
    }

    public double getDescuento_grasa() {
        return descuento_grasa;
    }

    public void setDescuento_grasa(double descuento_grasa) {
        this.descuento_grasa = descuento_grasa;
    }

    public double getDescuento_st() {
        return descuento_st;
    }

    public void setDescuento_st(double descuento_st) {
        this.descuento_st = descuento_st;
    }

    public double getPago_total() {
        return pago_total;
    }

    public void setPago_total(double pago_total) {
        this.pago_total = pago_total;
    }

    public double getMonto_retencion() {
        return monto_retencion;
    }

    public void setMonto_retencion(double monto_retencion) {
        this.monto_retencion = monto_retencion;
    }

    public double getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(double monto_final) {
        this.monto_final = monto_final;
    }
}
