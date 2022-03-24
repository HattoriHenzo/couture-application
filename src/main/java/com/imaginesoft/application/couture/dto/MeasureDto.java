package com.imaginesoft.application.couture.dto;

public class MeasureDto {

    private Long id;
    private int value;
    private MeasureTypeDto measureType;

    public MeasureDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MeasureTypeDto getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureTypeDto measureType) {
        this.measureType = measureType;
    }
}
