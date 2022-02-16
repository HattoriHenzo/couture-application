package com.imaginesoft.application.couture.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MEASURE")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALUE")
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURE_TYPE_ID", referencedColumnName = "ID")
    private MeasureType measureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRESS_ID", referencedColumnName = "ID")
    private Dress dress;

    public Measure() {
        // Default constructor
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

    public MeasureType getMeasureType() {
        return measureType;
    }

    public void setMeasureType(MeasureType measureType) {
        this.measureType = measureType;
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Measure measure = (Measure) o;

        return Objects.equals(id, measure.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "value = " + value + ")";
    }
}
