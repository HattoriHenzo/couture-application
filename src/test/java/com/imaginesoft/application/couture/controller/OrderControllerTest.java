package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.MaterialTypeDto;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.dto.OrderDto;
import com.imaginesoft.application.couture.model.MaterialType;
import com.imaginesoft.application.couture.model.Order;
import com.imaginesoft.application.couture.service.OrderService;
import com.imaginesoft.application.couture.util.ApplicationDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.Clock;

import static com.imaginesoft.application.couture.TestDataFactory.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest extends BaseControllerTest {

    @MockBean
    private OrderService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var order = createNewOrder();
        var orderResponse = createNewOrderDto();

        when(service.findById(anyLong())).thenReturn(order);
        when(mapper.performMapping(order, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/orders/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewOrder());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/orders/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(createNewOrder());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var order = createNewOrder();
        var orders = createNewOrders();
        var orderResponse = createNewOrderDto();

        when(service.findAll()).thenReturn(orders);
        when(mapper.performMapping(order, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.data", hasSize(greaterThan(0)))
                );
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_404_BAD_REQUEST() throws Exception {
        when(service.findAll()).thenReturn(createNewOrders());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenMaterialType_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var orderRequest = BAD_BODY;
        var orderToCreate = createNewOrder();
        var createdOrder = createNewOrder();
        var orderResponse = createNewOrderDto();

        when(mapper.performMapping(orderRequest, Order.class)).thenReturn(orderToCreate);
        when(service.createOrUpdate(orderToCreate)).thenReturn(createdOrder);
        when(mapper.performMapping(createdOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMaterialType_whenCallCreate_thenReturns_200_OK() throws Exception {
        var orderRequest = createNewOrderDto();
        var orderToCreate = createNewOrder();
        var createdOrder = createNewOrder();
        var orderResponse = createNewOrderDto();

        when(mapper.performMapping(orderRequest, Order.class)).thenReturn(orderToCreate);
        when(service.createOrUpdate(orderToCreate)).thenReturn(createdOrder);
        when(mapper.performMapping(createdOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMeasureType_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var orderRequest = createNewOrder();
        var orderToCreate = createNewOrder();
        var createdOrder = createNewOrder();
        var orderResponse = createNewOrderDto();

        when(mapper.performMapping(orderRequest, Order.class)).thenReturn(orderToCreate);
        when(service.createOrUpdate(orderToCreate)).thenReturn(createdOrder);
        when(mapper.performMapping(createdOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMaterialType_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var orderRequest = BAD_BODY;
        var orderToCreate = createNewOrder();
        var createdOrder = createNewOrder();
        var orderResponse = createNewOrderDto();

        when(mapper.performMapping(orderRequest, Order.class)).thenReturn(orderToCreate);
        when(service.createOrUpdate(orderToCreate)).thenReturn(createdOrder);
        when(mapper.performMapping(createdOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMaterialType_whenCallDelete_thenReturns_200_OK() throws Exception {
        var orderToDelete = createNewOrder();
        var deletedOrder = orderToDelete;
        var orderResponse = createNewOrderDto();

        when(service.findById(ID)).thenReturn(orderToDelete);
        when(service.delete(orderToDelete.getId())).thenReturn(deletedOrder);
        when(mapper.performMapping(deletedOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/orders/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("deleted")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenMaterialType_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var orderToDelete = createNewOrder();
        var deletedOrder = orderToDelete;
        var orderResponse = createNewOrderDto();

        when(service.findById(ID)).thenReturn(orderToDelete);
        when(service.delete(orderToDelete.getId())).thenReturn(deletedOrder);
        when(mapper.performMapping(deletedOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/orders/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenMaterialType_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var orderToDelete = createNewOrder();
        var deletedOrder = orderToDelete;
        var orderResponse = createNewOrderDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(orderToDelete.getId())).thenReturn(deletedOrder);
        when(mapper.performMapping(deletedOrder, OrderDto.class)).thenReturn(orderResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/orders/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}
