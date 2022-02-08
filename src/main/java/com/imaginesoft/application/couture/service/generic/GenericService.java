package com.imaginesoft.application.couture.service.generic;

import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

public abstract class GenericService<T> {

    private ValidatorFactory factory;
    private Validator validator;
    private Set<ConstraintViolation<T>> violations;

    protected void validateDomainRecord(T object) {
        violations = getConstraintViolations(object);
        if(!violations.isEmpty()) {
            throw new DomainRulesException(violations);
        }
    }

    protected void validateDomainRecord(T object, String message) {
        violations = getConstraintViolations(object);
        if(!violations.isEmpty()) {
            throw new DomainRulesException(message, violations);
        }
    }

    private Set<ConstraintViolation<T>> getConstraintViolations(T object) {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        return validator.validate(object);
    }

    public abstract T findById(Long id) throws RecordNotFoundException;
    public abstract List<T> findAll() throws RecordNotFoundException;
    public abstract T createOrUpdate(T object);
    public abstract T delete(T object);

}
