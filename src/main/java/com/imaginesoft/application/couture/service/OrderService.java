package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.repository.OrderRepository;
import com.imaginesoft.application.couture.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService extends GenericService<Order> {

    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order findById(Long id) throws RecordNotFoundException {
        var order = repository.findById(id);
        return order.orElseThrow(
                () -> new RecordNotFoundException("No record found")
        );
    }

    @Override
    public List<Order> findAll() throws RecordNotFoundException {
        var orders = repository.findAll();
        if(orders.isEmpty()) {
            throw new RecordNotFoundException("No record found");
        }
        return orders;
    }

    @Override
    public Order createOrUpdate(Order order) {
        validateDomainRecord(order);
        return repository.save(order);
    }

    @Override
    public Order delete(Order order) {
        var orderToDelete = repository.findById(order.getId());
        var deletedOrder = new AtomicReference<Order>();

        orderToDelete.ifPresent(value -> {
            repository.delete(value);
            deletedOrder.set(value);
        });

        return deletedOrder.get();
    }
}
