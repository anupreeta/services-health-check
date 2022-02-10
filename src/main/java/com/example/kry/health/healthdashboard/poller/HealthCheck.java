package com.example.kry.health.healthdashboard.poller;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import com.example.kry.health.healthdashboard.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class HealthCheck {

    @Autowired
    private ServiceRepository serviceRepository;

    @Scheduled(fixedRate = 30000)
    public void run() {
        log.info("Starting to poll health of all services.");

        RestTemplate restTemplate = new RestTemplate();

        Iterable<ServiceEntity> services = serviceRepository.findAll();

        for (ServiceEntity service : services) {
            try {
                ResponseEntity<String> result = restTemplate.getForEntity(service.getUrl(), String.class);
                HttpStatus status = result.getStatusCode();
                log.info("Status received for service {}: {}", service.getName(), status);

                //String status = result.getStatusCode().is2xxSuccessful() ? String.valueOf(HttpStatus.OK) : "FAIL";
                //service.setStatus(status);

                // Check status of the service and set it accordingly
                if (status.equals(HttpStatus.OK)) {
                    service.setStatus(status.getReasonPhrase());
                }


                // Save new status to repository
                serviceRepository.save(service);
                log.info("Service status saved", service.getStatus());
            } catch (Exception e) {
                service.setStatus("FAIL");
                serviceRepository.save(service);
                log.info("Error in fetching status of {} with url {}. Status set to: FAIL",
                        service.getName(), service.getUrl());
                log.error(String.valueOf(e));
            }
        }

        log.info("Polling of health of the services is done.");

    }
}
