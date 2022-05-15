package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.controller.generic.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.OrderDto;
import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.service.generic.GenericService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import com.imaginesoft.application.couture.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ApplicationDataFactory.API_V1_APPLICATION)
public class OrderController extends GenericController<OrderDto, Order> {

    @Autowired
    protected OrderController(GenericService<Order> service, MapperWrapper mapper, DateTimeWrapper dateTime) {
        super(service, mapper, dateTime, OrderDto.class, Order.class);
    }

    @Override
    @GetMapping("/orders/{id}")
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/orders")
    protected ResponseEntity<Response> findAll() throws RecordNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/orders")
    protected ResponseEntity<Response> create(@RequestBody OrderDto orderDto) {
        return super.create(orderDto);
    }

    @Override
    @PutMapping("/orders")
    protected ResponseEntity<Response> update(@RequestBody OrderDto orderDto) {
        return super.update(orderDto);
    }

    @Override
    @DeleteMapping("/orders/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        return super.delete(id);
    }
}
