package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.controller.exception.BadRequestException;
import com.imaginesoft.application.couture.controller.exception.ResourceNotFoundException;
import com.imaginesoft.application.couture.generic.controller.GenericController;
import com.imaginesoft.application.couture.controller.message.Response;
import com.imaginesoft.application.couture.dto.OrderDto;
import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.generic.service.GenericService;
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
    protected ResponseEntity<Response> findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.findById(id);
    }

    @Override
    @GetMapping("/orders")
    protected ResponseEntity<Response> findAll() throws ResourceNotFoundException {
        return super.findAll();
    }

    @Override
    @PostMapping("/orders")
    protected ResponseEntity<Response> create(@RequestBody OrderDto orderDto) throws BadRequestException {
        return super.create(orderDto);
    }

    @Override
    @PutMapping("/orders")
    protected ResponseEntity<Response> update(@RequestBody OrderDto orderDto) throws BadRequestException {
        return super.update(orderDto);
    }

    @Override
    @DeleteMapping("/orders/{id}")
    protected ResponseEntity<Response> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.delete(id);
    }
}
