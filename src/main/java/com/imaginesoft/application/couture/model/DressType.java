package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DRESS_TYPE")
public class DressType extends GenericType {

    @OneToMany(mappedBy = "dressType")
    private List<Dress> dresses;

    public DressType() {
        super();
    }

    public List<Dress> getDresses() {
        return dresses;
    }

    public void setDresses(List<Dress> dresses) {
        this.dresses = dresses;
    }
}
