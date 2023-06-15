package com.usach.grasasolidoservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grasa_solido")
@AllArgsConstructor
@NoArgsConstructor
public class GrasaSolidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long ID;
    @Column(nullable = false, length = 5)
    private String proveedor;
    @Column(nullable = false)
    private Double grasa;
    @Column(nullable = false)
    private Double solido;

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

