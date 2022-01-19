package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "MODEL_TYPE")
public class ModelType extends GenericType {

    public ModelType() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ModelType modelType = (ModelType) o;
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
