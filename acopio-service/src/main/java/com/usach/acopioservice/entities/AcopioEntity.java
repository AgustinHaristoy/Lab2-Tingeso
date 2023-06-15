package com.usach.acopioservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "acopio")
@AllArgsConstructor
@NoArgsConstructor
public class AcopioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String fecha;
    @Column(nullable = false, length = 1)
    private String turno;
    @Column(nullable = false, length = 5)
    private String proveedor;
    @Column(nullable = false)
    private Double kls_leche;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getKls_leche() {
        return kls_leche;
    }

    public void setKls_leche(Double kls_leche) {
        this.kls_leche = kls_leche;
    }
}
