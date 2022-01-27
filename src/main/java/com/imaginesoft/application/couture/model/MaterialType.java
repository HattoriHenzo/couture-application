package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaterialType that = (MaterialType) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "name = " + getName() + ", " +
                "image = " + getImage() + ")";
    }
}
