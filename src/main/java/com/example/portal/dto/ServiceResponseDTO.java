package com.example.portal.dto;

public class ServiceResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;

    public ServiceResponseDTO(Long id,
                              String title,
                              String description,
                              String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}