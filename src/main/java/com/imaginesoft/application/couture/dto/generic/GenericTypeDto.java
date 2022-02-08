package com.imaginesoft.application.couture.dto.generic;

public abstract class GenericTypeDto {

    private Long id;
    private String name;

    protected GenericTypeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
