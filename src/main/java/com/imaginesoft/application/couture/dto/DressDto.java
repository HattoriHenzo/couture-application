package com.imaginesoft.application.couture.dto;

import com.imaginesoft.application.couture.model.Measure;

import java.util.List;

public class DressDto {

    private Long id;
    private int amount;
    private List<Measure> measures;
    private String dressTypeName;
    private String modelTypeName;
    private String materialTypeName;

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

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public String getDressTypeName() {
        return dressTypeName;
    }

    public void setDressTypeName(String dressTypeName) {
        this.dressTypeName = dressTypeName;
    }

    public String getModelTypeName() {
        return modelTypeName;
    }

    public void setModelTypeName(String modelTypeName) {
        this.modelTypeName = modelTypeName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}
