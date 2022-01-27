package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Dress;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressService extends GenericService<Dress> {
    @Override
    public Dress getById(Long id) {
        return null;
    }

    @Override
    public List<Dress> getAll() {
        return null;
    }

    @Override
    public Dress createOrUpdate(Dress dress) {
        return null;
    }

    @Override
    public Dress delete(Dress dress) {
        return null;
    }
}
