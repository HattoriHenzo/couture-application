package com.imaginesoft.application.couture.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.imaginesoft.application.couture.model.generic.GenericType;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "model_type")
public class ModelType extends GenericType {

    @OneToMany(mappedBy = "modelType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Dress> dresses;

    public ModelType() {
        super();
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
        var modelType = (ModelType) o;
        return getId() != null && Objects.equals(getId(), modelType.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "name = " + getName() + ")";
    }
}
