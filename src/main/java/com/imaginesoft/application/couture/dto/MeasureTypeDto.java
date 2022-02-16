package com.imaginesoft.application.couture.dto;

import com.imaginesoft.application.couture.dto.generic.GenericTypeDto;
import com.imaginesoft.application.couture.model.Measure;

import java.util.List;

public class MeasureTypeDto extends GenericTypeDto {

    public MeasureTypeDto() {
        super();
    }

    private List<Measure> measures;

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
