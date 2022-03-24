package com.imaginesoft.application.couture.dto;

import java.util.List;

public class DressDto {

    private Long id;
    private int amount;
    private List<MeasureDto> measures;
    private DressTypeDto dressType;
    private ModelTypeDto modelType;
    private MaterialTypeDto materialType;

    public DressDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<MeasureDto> getMeasures() {
        return measures;
    }

    public void setMeasures(List<MeasureDto> measures) {
        this.measures = measures;
    }

    public DressTypeDto getDressType() {
        return dressType;
    }

    public void setDressType(DressTypeDto dressType) {
        this.dressType = dressType;
    }

    public ModelTypeDto getModelType() {
        return modelType;
    }

    public void setModelType(ModelTypeDto modelType) {
        this.modelType = modelType;
    }

    public MaterialTypeDto getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialTypeDto materialType) {
        this.materialType = materialType;
    }
}
