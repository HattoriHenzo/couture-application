package com.imaginesoft.application.couture.service;

import java.util.List;

public interface GenericService<T> {
    T getById(Long id);
    List<T> getAll();
    boolean create(T object);
    boolean update(T object);
    boolean delete(Long id);
}
