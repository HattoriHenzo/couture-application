package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Dress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DressService implements GenericService<Dress> {
    @Override
    public Dress getById(Long id) {
        return null;
    }

    @Override
    public List<Dress> getAll() {
        return null;
    }

    @Override
    public boolean create(Dress object) {
        return false;
    }

    @Override
    public boolean update(Dress object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
