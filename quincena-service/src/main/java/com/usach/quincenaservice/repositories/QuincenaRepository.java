package com.usach.quincenaservice.repositories;

import com.usach.quincenaservice.entities.QuincenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuincenaRepository extends JpaRepository<QuincenaEntity, Long> {
    List<QuincenaEntity> findByProveedor(String proveedor);
}
