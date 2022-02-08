package com.example.kry.health.healthdashboard.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;
    private String status;
    private Timestamp created;
    private Timestamp modified;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = Timestamp.valueOf(LocalDateTime.now());
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified() {
        this.modified = Timestamp.valueOf(LocalDateTime.now());
    }





}
