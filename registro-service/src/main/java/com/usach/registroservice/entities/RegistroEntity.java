package com.usach.registroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registro")
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, length = 5)
    private String proveedor;
    private String fecha;
    private String turno;
    private Double kls_leche;
    private Double grasa;
    private Double solido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Double getKls_leche() {
        return kls_leche;
    }

    public void setKls_leche(Double kls_leche) {
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
}
