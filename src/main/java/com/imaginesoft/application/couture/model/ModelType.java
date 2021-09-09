package com.imaginesoft.application.couture.model;

import com.imaginesoft.application.couture.model.generic.GenericType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MODEL_TYPE")
public class ModelType extends GenericType {

    public ModelType() {
        super();
    }
}
