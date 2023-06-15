package com.usach.proveedorservice.repositories;

import com.usach.proveedorservice.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, String> {

    @Query("select e from ProveedorEntity e where e.codigo_proveedor = :codigo_proveedor")
    ProveedorEntity findByCodigoProveedor(@Param("codigo_proveedor")String codigo_proveedor);
}
