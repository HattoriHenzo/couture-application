package com.imaginesoft.application.couture.repository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.imaginesoft.application.couture.util.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ActiveProfiles("test")
class OrdersRepositoryTest implements WithAssertions {

    @Autowired
    private OrdersRepository repository;

    @Test
    void givenOrders_whenCreateOrders_thenOrdersExists() {

        var newOrders = createNewOrders();
        var createdOrders = repository.save(newOrders);

        assertAll(
                () -> assertThat(createdOrders).isNotNull(),
                () -> assertThat(createdOrders.getNumber()).isEqualTo(ORDERS_NUMBER),
                () -> assertThat(createdOrders.getClient()).isNotNull(),
                () -> assertThat(createdOrders.getDate()).isEqualTo(ORDERS_DATE),
                () -> assertThat(createdOrders.getDeliveryDate()).isEqualTo(ORDERS_DELIVERY_DATE)
        );
    }

    @Test
    void givenOrders_whenUpdateOrders_thenOrdersHasChanged() {

        var ordersToUpdate = repository.findById(ORDERS_ID);
        ordersToUpdate.ifPresent(value -> value.setNumber(ORDERS_EDITED_NUMBER));

        var updatedOrders = repository.save(ordersToUpdate.get());

        assertAll(
                () -> assertThat(updatedOrders.getId()).isEqualTo(ordersToUpdate.get().getId()),
                () -> assertThat(updatedOrders.getNumber()).isEqualTo(ORDERS_EDITED_NUMBER)
        );
    }

    @Test
    void givenOrders_whenDeleteOrders_thenOrdersDoesNotExists() {

        var ordersToDelete = repository.findById(ORDERS_ID);
        repository.delete(ordersToDelete.get());
        var deletedOrders = repository.findById(ORDERS_ID);

        assertThat(deletedOrders).isNotPresent();
    }
}