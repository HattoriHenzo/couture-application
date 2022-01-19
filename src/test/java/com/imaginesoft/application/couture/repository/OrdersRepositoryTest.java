package com.imaginesoft.application.couture.repository;

import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.model.Orders;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = {"classpath:application-h2-test.properties"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersRepositoryTest {

    private final Long ID_3 = 3L;
    private final String NUMBER = "0001";
    private final String EDITED_NUMBER = "0002";
    private final LocalDateTime DATE = LocalDateTime.now();
    private final LocalDateTime DELIVERY_DATE = LocalDateTime.now();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrdersRepository repository;

    @Test
    @Order(1)
    void givenOrders_whenCreateOrders_thenOrdersExists() {

        Orders newOrders = createNewOrders();
        Orders createdOrders = entityManager.persist(newOrders);

        Assertions.assertAll(
                () -> assertThat(createdOrders).isNotNull(),
                () -> assertThat(createdOrders.getNumber()).isEqualTo(NUMBER),
                () -> assertThat(createdOrders.getClient()).isNotNull(),
                () -> assertThat(createdOrders.getDate()).isEqualTo(DATE),
                () -> assertThat(createdOrders.getDeliveryDate()).isEqualTo(DELIVERY_DATE)
        );
    }

    @Test
    @Order(2)
    void givenOrders_whenUpdateOrders_thenOrdersHasChanged() {

        Orders newOrders = createNewOrders();

        Orders ordersToUpdate = entityManager.persist(newOrders);
        ordersToUpdate.setNumber(EDITED_NUMBER);

        Orders updatedOrders = repository.save(ordersToUpdate);

        Assertions.assertAll(
                () -> assertThat(updatedOrders.getId()).isEqualTo(ordersToUpdate.getId()),
                () -> assertThat(updatedOrders.getNumber()).isEqualTo(EDITED_NUMBER)
        );
    }

    @Test
    @Order(3)
    void givenOrders_whenDeleteOrders_thenOrdersDoesNotExists() {

        Orders newOrders =  createNewOrders();

        Orders ordersToDelete = entityManager.persist(newOrders);
        repository.delete(ordersToDelete);

        Optional<Orders> deletedOrders = repository.findById(ID_3);

        assertThat(deletedOrders).isNotPresent();
    }

    private Orders createNewOrders() {
        Orders newOrders = new Orders();
        newOrders.setNumber(NUMBER);
        newOrders.setClient(new Client());
        newOrders.setDate(DATE);
        newOrders.setDeliveryDate(DELIVERY_DATE);
        return newOrders;
    }
}