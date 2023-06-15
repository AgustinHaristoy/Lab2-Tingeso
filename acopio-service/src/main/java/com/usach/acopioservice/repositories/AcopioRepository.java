package com.usach.acopioservice.repositories;

import com.usach.acopioservice.entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, Long> {
    @Query("select e from AcopioEntity e where e.id = :id")
    AcopioEntity findByID(@Param("id")Long id);

}
