package com.imaginesoft.application.couture.service.generic;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public abstract class GenericService<M> {

    private ValidatorFactory factory;
    private Validator validator;
    private Set<ConstraintViolation<M>> violations;

    protected final JpaRepository<M, Long> repository;

    protected GenericService(JpaRepository<M, Long> repository) {
        this.repository = repository;
    }

    protected void validateDomainRecord(M object) {
        violations = getConstraintViolations(object);
        if(!violations.isEmpty()) {
            throw new DomainRulesException(violations);
        }
    }

    protected void validateDomainRecord(M object, String message) {
        violations = getConstraintViolations(object);
        if(!violations.isEmpty()) {
            throw new DomainRulesException(message, violations);
        }
    }

    private Set<ConstraintViolation<M>> getConstraintViolations(M object) {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        return validator.validate(object);
    }

    public M findById(Long id) throws RecordNotFoundException {
        var model = repository.findById(id);
        return model.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    public List<M> findAll() throws RecordNotFoundException {
        var models = repository.findAll();
        if(models.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return models;
    }

    public M createOrUpdate(M model) {
        validateDomainRecord(model);
        return repository.save(model);
    }

    public M delete(Long id) {
        var modelToDelete = repository.findById(id);
        var deletedModel = new AtomicReference<M>();

        modelToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedModel.set(modelToDelete.get());
        });

        return deletedModel.get();
    }
}
