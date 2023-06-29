package com.usach.planillapagosservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planillapagos")
@AllArgsConstructor
@NoArgsConstructor
public class PlanillaPagosEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String quincena;
    private String proveedor;
    private String nombre;
    private Double total_kls_leche;
    private Integer dias;
    private Double promedio_kls_leche;
    private Double variacion_leche;
    private Double grasa;
    private Double variacion_grasa;
    private Double solidos_totales;
    private Double variacion_st;
    private Double pago_leche;
    private Double pago_grasa;
    private Double pago_st;
    private Double bonificacion_frecuencia;
    private Double descuento_leche;
    private Double descuento_grasa;
    private Double descuento_st;
    private Double pago_total;
    private Double monto_retencion;
    private Double monto_final;

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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTotal_kls_leche() {
        return total_kls_leche;
    }

    public void setTotal_kls_leche(Double total_kls_leche) {
        this.total_kls_leche = total_kls_leche;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getPromedio_kls_leche() {
        return promedio_kls_leche;
    }

    public void setPromedio_kls_leche(Double promedio_kls_leche) {
        this.promedio_kls_leche = promedio_kls_leche;
    }

    public Double getVariacion_leche() {
        return variacion_leche;
    }

    public void setVariacion_leche(Double variacion_leche) {
        this.variacion_leche = variacion_leche;
    }

    public Double getGrasa() {
        return grasa;
    }

    public void setGrasa(Double grasa) {
        this.grasa = grasa;
    }

    public Double getVariacion_grasa() {
        return variacion_grasa;
    }

    public void setVariacion_grasa(Double variacion_grasa) {
        this.variacion_grasa = variacion_grasa;
    }

    public Double getSolidos_totales() {
        return solidos_totales;
    }

    public void setSolidos_totales(Double solidos_totales) {
        this.solidos_totales = solidos_totales;
    }

    public Double getVariacion_st() {
        return variacion_st;
    }

    public void setVariacion_st(Double variacion_st) {
        this.variacion_st = variacion_st;
    }

    public Double getPago_leche() {
        return pago_leche;
    }

    public void setPago_leche(Double pago_leche) {
        this.pago_leche = pago_leche;
    }

    public Double getPago_grasa() {
        return pago_grasa;
    }

    public void setPago_grasa(Double pago_grasa) {
        this.pago_grasa = pago_grasa;
    }

    public Double getPago_st() {
        return pago_st;
    }

    public void setPago_st(Double pago_st) {
        this.pago_st = pago_st;
    }

    public Double getBonificacion_frecuencia() {
        return bonificacion_frecuencia;
    }

    public void setBonificacion_frecuencia(Double bonificacion_frecuencia) {
        this.bonificacion_frecuencia = bonificacion_frecuencia;
    }

    public Double getDescuento_leche() {
        return descuento_leche;
    }

    public void setDescuento_leche(Double descuento_leche) {
        this.descuento_leche = descuento_leche;
    }

    public Double getDescuento_grasa() {
        return descuento_grasa;
    }

    public void setDescuento_grasa(Double descuento_grasa) {
        this.descuento_grasa = descuento_grasa;
    }

    public Double getDescuento_st() {
        return descuento_st;
    }

    public void setDescuento_st(Double descuento_st) {
        this.descuento_st = descuento_st;
    }

    public Double getPago_total() {
        return pago_total;
    }

    public void setPago_total(Double pago_total) {
        this.pago_total = pago_total;
    }

    public Double getMonto_retencion() {
        return monto_retencion;
    }

    public void setMonto_retencion(Double monto_retencion) {
        this.monto_retencion = monto_retencion;
    }

    public Double getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(Double monto_final) {
        this.monto_final = monto_final;
    }
}
