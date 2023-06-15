package com.usach.grasasolidoservice.repositories;

import com.usach.grasasolidoservice.entities.GrasaSolidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrasaSolidoRepository extends JpaRepository<GrasaSolidoEntity, Long> {
    public GrasaSolidoEntity findByProveedor(String proveedor);

    public GrasaSolidoEntity findByID(Long id);
}
