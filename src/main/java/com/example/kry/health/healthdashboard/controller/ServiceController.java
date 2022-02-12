package com.example.kry.health.healthdashboard.controller;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import com.example.kry.health.healthdashboard.model.ServiceModel;
import com.example.kry.health.healthdashboard.service.HealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

    @Autowired
    private HealthService service;

    @PostMapping(path ="/save")
    public ServiceEntity saveService(@RequestBody ServiceEntity entity) {
        return service.save(entity);
    }

    @GetMapping(path = "/list")
    public List<ServiceEntity> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/{id}")
    public ServiceEntity getServiceById(@PathVariable("id") Integer id) {
        log.info("Retrieving details of service with : " + id);
        return service.findServiceById(id);
    }

    @PostMapping(path = "/{id}")
    public ServiceEntity updateService(@PathVariable("id") Integer id, @RequestBody ServiceModel entity) {
        log.info("Updating details of service with : " + id);
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        log.info("Deleting service with : " + id);
        service.delete(id);
    }
}
