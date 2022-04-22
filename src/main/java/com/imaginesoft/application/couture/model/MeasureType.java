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
@Table(name = "measure_type")
public class MeasureType extends GenericType {

    @OneToMany(
            mappedBy = "measureType",
            cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Measure> measures;

    public MeasureType() {
        // Default constructor
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MeasureType that = (MeasureType) o;
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
                "name = " + getName() + ")";
    }
}
