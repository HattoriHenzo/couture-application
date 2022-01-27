package com.imaginesoft.application.couture.util;

import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.model.Client;
import com.imaginesoft.application.couture.model.Gender;
import com.imaginesoft.application.couture.model.Orders;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static final Long CLIENT_ID = 1L;
    public static final Long CLIENT_ADDED_ID = 6L;
    public static final Long CLIENT_DELETED_ID = 3L;
    public static final int NUMBER_OF_CLIENTS = 5;
    public static final String FIRST_NAME = "CARLOS";
    public static final String EDITED_FIRST_NAME = "THIERRY";
    public static final String LAST_NAME = "JEANS";
    public static final String TELEPHONE = "514-999-4800";
    public static final String EDITED_TELEPHONE = "418-333-9988";

    public static Client createNewClient() {
        Client newClient = new Client();

        newClient.setId(CLIENT_ID );
        newClient.setFirstName(FIRST_NAME);
        newClient.setLastName(LAST_NAME);
        newClient.setGender(Gender.MALE);
        newClient.setTelephone(TELEPHONE);

        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders());
        ordersList.add(new Orders());
        newClient.setOrders(ordersList);

        return newClient;
    }

    public static ClientDto createNewClientDto() {
        ClientDto newClientDto = new ClientDto();

        newClientDto.setId(CLIENT_ID );
        newClientDto.setFirstName(FIRST_NAME);
        newClientDto.setLastName(LAST_NAME);
        newClientDto.setGender(Gender.MALE.name());
        newClientDto.setTelephone(TELEPHONE);

        return newClientDto;
    }

    public static List<Client> createNewClients() {
        return List.of(new Client(), new Client());
    }

    public static List<ClientDto> createNewClientDtos() {
        return List.of(new ClientDto(), new ClientDto());
    }
}
