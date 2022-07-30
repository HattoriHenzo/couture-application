package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.repository.OrderRepository;
import com.imaginesoft.application.couture.generic.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends GenericService<Order> {

    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository);
    }
}
