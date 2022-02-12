package com.example.kry.health.healthdashboard.service;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import com.example.kry.health.healthdashboard.model.ServiceModel;
import com.example.kry.health.healthdashboard.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HealthService {
    @Autowired
    private ServiceRepository repository;

    enum status {
        OK,
        FAIL,
        UNKNOWN //default status
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
        Optional<ServiceEntity> findService = repository.findById(id);

        if(findService.isEmpty()) {
            throw new EntityNotFoundException("Request service with id: " + id + " could not be found");
        }
        return findService.get();
    }

    public void delete(Integer id) {
        try {
            repository.findById(id).get();
            repository.deleteById(id);
        } catch (EntityNotFoundException entityNotFoundException){
            throw new EntityNotFoundException("Request service with id: " + id + " could not be found");
        }
    }

    public ServiceEntity update(final Integer id, final ServiceModel service) {
        try {
            ServiceEntity entityToUpdate = repository.findById(id).get();

            entityToUpdate.setName(service.getName());
            entityToUpdate.setUrl(service.getUrl());
            return repository.save(entityToUpdate);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new EntityNotFoundException("Request service with id: " + id + " could not be found");
        }
    }
}
