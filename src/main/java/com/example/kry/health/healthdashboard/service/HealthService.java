package com.example.kry.health.healthdashboard.service;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import com.example.kry.health.healthdashboard.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HealthService {
    @Autowired
    private ServiceRepository repository;

    enum status {
        OK,
        FAIL,
        UNKNOWN
    }

    public ServiceEntity save(ServiceEntity service) {
        log.info("Saving service with name: " + service.getName() + " and url:" + service.getUrl());
        service.setStatus(String.valueOf(status.UNKNOWN));
        service.setCreated();
        service.setModified();
        return repository.save(service);
    }

    public List<ServiceEntity> getAll() {
        log.info("Retrieving list of all services");
        return repository.findAll();
    }

    public ServiceEntity findServiceById(Integer id) {
        return repository.getById(id);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
