package com.usach.proveedorservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "proveedor")
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {
    @Id
    @Column(nullable=false, length = 5)
    @NotEmpty
    @Pattern(regexp = "[0-9]{5}")
    private String codigo_proveedor;
    @Column(nullable = false, length = 45)
    @NotEmpty
    private String nombre;
    @Column(nullable = false, length = 1)
    @NotEmpty
    @Pattern(regexp = "[A-D]{1}")
    private String categoria;
    @Column(nullable = false)
    private Boolean retencion;

    public String getCodigo_proveedor() {
        return codigo_proveedor;
    }

    public void setCodigo_proveedor(String codigo_proveedor) {
        this.codigo_proveedor = codigo_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getRetencion() {
        return retencion;
    }

    public void setRetencion(Boolean retencion) {
        this.retencion = retencion;
    }
}
