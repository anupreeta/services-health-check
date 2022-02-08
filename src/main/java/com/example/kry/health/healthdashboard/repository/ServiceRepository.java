package com.example.kry.health.healthdashboard.repository;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
}
