package com.usach.quincenaservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quincena")
@AllArgsConstructor
@NoArgsConstructor
public class QuincenaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public double getKls_leche() {
        return kls_leche;
    }

    public void setKls_leche(double kls_leche) {
        this.kls_leche = kls_leche;
    }

    public Double getGrasa() {
        return grasa;
    }

    public void setGrasa(Double grasa) {
        this.grasa = grasa;
    }

    public Double getSolido() {
        return solido;
    }

    public void setSolido(Double solido) {
        this.solido = solido;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Integer bonificacion) {
        this.bonificacion = bonificacion;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public boolean isAnterior() {
        return anterior;
    }

    public void setAnterior(boolean anterior) {
        this.anterior = anterior;
    }
}
