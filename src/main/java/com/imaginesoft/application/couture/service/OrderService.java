package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Orders;
import com.imaginesoft.application.couture.repository.OrdersRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends GenericService<Orders> {

    private OrdersRepository repository;

    @Autowired
    public OrderService(OrdersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Orders findById(Long id) {
        return null;
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders createOrUpdate(Orders object) {
        return null;
    }

    @Override
    public Orders delete(Orders order) {
        return null;
    }
}
