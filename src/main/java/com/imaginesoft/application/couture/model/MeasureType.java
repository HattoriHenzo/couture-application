package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "MEASURE_TYPE")
public class MeasureType extends GenericType {

    @OneToMany(mappedBy = "measureType")
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
}
