package com.example.kry.health.healthdashboard.model;

import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Value
@Builder
public class ServiceModel {
    String id;
    @NotNull(message = "Name should not be null")
    @NotEmpty(message = "Name should not be empty")
    String name;
    @NotNull(message = "Health check url should not be null")
    @NotEmpty(message = "Health check url should not be empty")
    @URL(message = "Health check url should be valid")
    String url;
    String status;
    Timestamp created;
    Timestamp modified;
}
