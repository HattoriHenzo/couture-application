package com.imaginesoft.application.couture.service;

import com.imaginesoft.application.couture.controller.exception.RecordNotFoundException;
import com.imaginesoft.application.couture.repository.OrderRepository;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest implements WithAssertions {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService underTest;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenOrders_whenFindingOrders_thenFindAllOrders() throws RecordNotFoundException {
        when(repository.findAll()).thenReturn(createNewOrders());
        assertThat(underTest.findAll()).isNotEmpty();
    }

    @Test
    void givenOrder_whenCreateOrder_thenOrderIsCreated() {
        var newOrder = createNewOrder();
        when(repository.save(newOrder)).thenReturn(newOrder);
        var createdOrder = underTest.createOrUpdate(newOrder);

        assertThat(createdOrder).isNotNull();
    }

    @Test
    void givenOrder_whenNumberIsEmpty_thenOrderThrowException() {
        var newOrder = createNewOrder();
        newOrder.setNumber(EMPTY_STRING);
        var exception = assertThrows(DomainRulesException.class,
                () -> underTest.createOrUpdate(newOrder));

        assertThat(exception.getMessage()).contains("can't be empty");
    }

    @Test
    void givenOrder_whenUpdateOrder_thenOrderIsUpdated() {
        var order = createNewOrder();
        order.setNumber(ORDER_EDITED_NUMBER);
        when(repository.save(order)).thenReturn(order);
        when(repository.findById(anyLong())).thenReturn(Optional.of(order));

        assertAll(
                () -> assertThat(underTest.createOrUpdate(order)).isNotNull(),
                () -> {
                    var updatedModelType = underTest.findById(1L);
                    assertThat(updatedModelType.getNumber()).isEqualTo(order.getNumber());
                }
        );
    }

    @Test
    void givenOrder_whenDeleteOrder_thenOrderIsDeleted() {
        var orderToDelete = createNewOrder();
        when(repository.findById(anyLong())).thenReturn(Optional.of(orderToDelete));
        var deletedOrder = underTest.delete(orderToDelete.getId());

        assertThat(deletedOrder).isNotNull();
    }
}
