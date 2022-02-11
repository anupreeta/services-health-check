package com.example.kry.health.healthdashboard.service;

import com.example.kry.health.healthdashboard.entity.ServiceEntity;
import com.example.kry.health.healthdashboard.model.ServiceModel;
import com.example.kry.health.healthdashboard.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HealthServiceTest {

    @InjectMocks
    HealthService target;

    @Mock
    ServiceRepository repository;

    @Test
    public void testGetAllServices() {
        ServiceEntity serviceEntity1 = createTestServiceEntity("test1-service", "https://test1.com");
        ServiceEntity serviceEntity2 = createTestServiceEntity("test2-service", "https://test2.com");

        when(repository.findAll()).thenReturn(List.of(serviceEntity1, serviceEntity2));

        List<ServiceEntity> response = target.getAll();

        assertEquals(2, response.size());
        assertEquals(response.get(0).getName(), "test1-service");
        assertEquals(response.get(1).getName(), "test2-service");
        assertEquals(response.get(0).getUrl(), "https://test1.com");
        assertEquals(response.get(1).getUrl(), "https://test2.com");
        System.out.println(response.get(0).getUrl());
        System.out.println(response.get(1).getUrl());
    }

    @Test
    public void testCreateService() {
        ServiceEntity createdEntity = createTestServiceEntity("test-service", "https://www.test.com");
        when(repository.save(any())).thenReturn(createdEntity);

        ServiceEntity response = target.save(createdEntity);

        assertEquals(createTestServiceFromEntity(createdEntity).getStatus(), response.getStatus());
    }


    public ServiceEntity createTestServiceEntity(final String name, final String url) {
        return ServiceEntity.builder()
                .id(1)
                .name(name)
                .url(url)
                .status("OK")
                .created(Timestamp.from(Instant.now()))
                .modified(Timestamp.from(Instant.now()))
                .build();
    }

    private ServiceModel createTestServiceFromEntity(final ServiceEntity serviceEntity) {
        return ServiceModel.builder()
                .id(String.valueOf(serviceEntity.getId()))
                .name(serviceEntity.getName())
                .url(serviceEntity.getUrl())
                .status("UNKNOWN")
                .build();
    }
}
