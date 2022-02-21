package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class OrderRepositoryTest implements WithAssertions {

    @Autowired
    private OrderRepository repository;

    @Test
    void givenOrder_whenCreateOrder_thenOrderExists() {
        var newOrder = createNewOrder();
        var createdOrder = repository.save(newOrder);

        assertAll(
                () -> assertThat(createdOrder).isNotNull(),
                () -> assertThat(createdOrder.getNumber()).isEqualTo(ORDER_NUMBER),
                () -> assertThat(createdOrder.getClient()).isNotNull(),
                () -> assertThat(createdOrder.getDate()).isEqualTo(ORDER_DATE),
                () -> assertThat(createdOrder.getDeliveryDate()).isEqualTo(ORDER_DELIVERY_DATE)
        );
    }

    @Test
    void givenOrder_whenUpdateOrder_thenOrderHasChanged() {
        var orderToUpdate = repository.findById(ORDER_ID);
        orderToUpdate.ifPresent(value -> value.setNumber(ORDER_EDITED_NUMBER));
        var updatedOrder = repository.save(orderToUpdate.get());

        assertAll(
                () -> assertThat(updatedOrder.getId()).isEqualTo(orderToUpdate.get().getId()),
                () -> assertThat(updatedOrder.getNumber()).isEqualTo(ORDER_EDITED_NUMBER)
        );
    }

    @Test
    void givenOrder_whenDeleteOrder_thenOrderDoesNotExists() {
        var orderToDelete = repository.findById(ORDER_ID);
        repository.delete(orderToDelete.get());
        var deletedOrder = repository.findById(ORDER_ID);

        assertThat(deletedOrder).isNotPresent();
    }
}