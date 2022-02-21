package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.repository.DressRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DressService extends GenericService<Dress> {

    private DressRepository repository;

    @Autowired
    public DressService(DressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dress findById(Long id) throws RecordNotFoundException {
        var dress = repository.findById(id);
        return dress.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<Dress> findAll() throws RecordNotFoundException {
        var dresses = repository.findAll();
        if(dresses.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return dresses;
    }

    @Override
    public Dress createOrUpdate(Dress dress) {
        validateDomainRecord(dress);
        return repository.save(dress);
    }

    @Override
    public Dress delete(Dress dress) {
        var dressToDelete = repository.findById(dress.getId());
        var deletedDress = new AtomicReference<Dress>();

        dressToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedDress.set(dressToDelete.get());
        });

        return deletedDress.get();
    }
}
