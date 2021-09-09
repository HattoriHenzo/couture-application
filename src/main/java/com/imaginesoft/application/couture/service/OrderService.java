package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements GenericService<Order> {

    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean create(Order object) {
        return false;
    }

    @Override
    public boolean update(Order object) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
