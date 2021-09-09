package com.imaginesoft.application.couture.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DRESS")
public class Dress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private int amount;

    @OneToMany(mappedBy = "dress")
    private List<Measure> measures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRESS_TYPE_ID")
    private DressType dressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_TYPE_ID")
    private ModelType modelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATERIAL_TYPE_ID")
    private MaterialType materialType;

    public Dress() {
        // Default constructor
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

    public DressType getDressType() {
        return dressType;
    }

    public void setDressType(DressType dressType) {
        this.dressType = dressType;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dress dress = (Dress) o;

        return Objects.equals(id, dress.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "amount = " + amount + ")";
    }
}
