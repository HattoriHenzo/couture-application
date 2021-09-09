package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIAL_TYPE")
public class MaterialType extends GenericType {

    @Column(name = "IMAGE")
    private String image;

    public MaterialType() {
        // Default constructor
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
