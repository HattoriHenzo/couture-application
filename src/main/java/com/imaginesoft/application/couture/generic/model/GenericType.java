package com.imaginesoft.application.couture.generic.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public abstract class GenericType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "The name can't be empty")
    @Column(name = "NAME")
    private String name;

    protected GenericType() {
        // Default constructor
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
