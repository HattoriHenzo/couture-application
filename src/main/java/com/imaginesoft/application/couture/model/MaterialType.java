package com.imaginesoft.application.couture.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.imaginesoft.application.couture.model.generic.GenericType;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "material_type")
public class MaterialType extends GenericType {

    @NotEmpty(message = "The image can't be empty")
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "materialType", cascade = {CascadeType.ALL, CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Dress> dresses;

    public MaterialType() {
        // Default constructor
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Dress> getDresses() {
        return dresses;
    }

    public void setDresses(List<Dress> dresses) {
        this.dresses = dresses;
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
