package com.imaginesoft.application.couture.it;

import com.fasterxml.jackson.core.type.TypeReference;
import com.imaginesoft.application.couture.controller.message.Success;
import com.imaginesoft.application.couture.dto.OrderDto;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderControllerITest extends BaseIntegrationTest {

    @Test
    void integrationTest_For_FindAll() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    assertThat(Objects.requireNonNull(result.getResponseBody()).getData()).isNotEmpty();
                });
    }

    @Test
    void integrationTest_For_FindById() {
        webTestClient.get()
                .uri(ApplicationDataFactory.API_V1 + "/orders/{ID}", ORDER_ID)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var orders = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<OrderDto>>() {
                            });
                    var foundOrder = orders.get(0);
                    assertAll(
                            () -> assertThat(foundOrder.getId()).isEqualTo(ORDER_ID),
                            () -> assertThat(foundOrder.getNumber()).isEqualTo(ORDER_NUMBER)
                    );
                });
    }

    @Test
    void integrationTest_For_Create() {
        var newOrder = createNewOrderDto();

        webTestClient.post()
                .uri(ApplicationDataFactory.API_V1 + "/orders")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(newOrder)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var orders = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<OrderDto>>() {
                            });
                    var createdOrder = orders.get(0);
                    assertAll(
                            () -> assertThat(createdOrder.getId()).isEqualTo(newOrder.getId()),
                            () -> assertThat(createdOrder.getNumber()).isEqualTo(newOrder.getNumber())
                    );
                });
    }

    @Test
    void integrationTest_For_Update() {
        var orderToUpdate = createNewOrderDto();
        orderToUpdate.setNumber(ORDER_EDITED_NUMBER);

        webTestClient.put()
                .uri(ApplicationDataFactory.API_V1 + "/orders")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(orderToUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Success.class)
                .consumeWith(result -> {
                    var orders = mapper.convertValue(Objects.requireNonNull(result.getResponseBody()).getData(),
                            new TypeReference<List<OrderDto>>() {
                            });
                    var updatedOrder = orders.get(0);
                    assertAll(
                            () -> assertThat(updatedOrder.getId()).isEqualTo(orderToUpdate.getId()),
                            () -> assertThat(updatedOrder.getNumber()).isEqualTo(orderToUpdate.getNumber())
                    );
                });
    }

    @Test
    void integrationTest_For_Delete() {
        webTestClient.delete()
                .uri(ApplicationDataFactory.API_V1 + "/orders/{ID}", ORDER_TO_DELETE)
                .exchange()
                .expectStatus().isOk();
    }
}
