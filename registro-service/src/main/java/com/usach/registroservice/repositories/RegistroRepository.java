package com.usach.registroservice.repositories;

import com.usach.registroservice.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {

    List<RegistroEntity> findByProveedor(String proveedor);
}
