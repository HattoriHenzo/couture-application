package com.imaginesoft.application.couture.util;

import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static final Long ID = 1L;
    public static final Long BAD_ID = 5L;
    public static final String BAD_URI = "/bad/uri";
    public static final String BAD_PATH_PARAM = "/\\param\\";
    public static final String BAD_BODY = "bad body";
    public static final String SUCCESS_DATE = "2022-01-04";

    public static final Long CLIENT_ID = 1L;
    public static final Long CLIENT_ADDED_ID = 6L;
    public static final Long CLIENT_TO_DELETE = 3L;
    public static final int NUMBER_OF_CLIENTS = 5;
    public static final String FIRST_NAME = "CARLOS";
    public static final String EDITED_FIRST_NAME = "THIERRY";
    public static final String LAST_NAME = "JEANS";
    public static final String TELEPHONE = "514-999-4800";
    public static final String EDITED_TELEPHONE = "418-333-9988";

    public static final Long DRESS_ID = 1L;
    public static final int DRESS_AMOUNT = 1000;

    public static final Long DRESS_TYPE_ID = 1L;
    public static final Long DRESS_TYPE_TO_DELETE = 2L;
    public static final String DRESS_TYPE_NAME = "PANTS";

    public static final Long MODEL_TYPE_ID = 3L;
    public static final String MODEL_TYPE_NAME = "Sherpa";
    public static final String MODEL_TYPE_EDITED_NAME = "African";

    public static Client createNewClient() {

        var newClient = new Client();
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

        var newClientDto = new ClientDto();
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

    public static Dress createNewDress() {

        var newDress = new Dress();
        newDress.setAmount(DRESS_AMOUNT);
        newDress.setDressType(new DressType());
        newDress.setModelType(new ModelType());
        newDress.setMaterialType(new MaterialType());
        newDress.setMeasures(createNewMeasures());

        return newDress;
    }

    public static List<Measure> createNewMeasures() {

        List<Measure> measures = new ArrayList<>();
        measures.add(new Measure());
        measures.add(new Measure());
        return measures;
    }

    public static DressType createNewDressType() {

        var newDressType = new DressType();
        newDressType.setId(DRESS_ID);
        newDressType.setName(DRESS_TYPE_NAME);

        return newDressType;
    }

    public static List<DressType> createNewDressTypes() {
        return List.of(
                new DressType(),
                new DressType(),
                new DressType()
        );
    }

    public static DressTypeDto createNewDressTypeDto() {

        var newDressTypeDto = new DressTypeDto();
        newDressTypeDto.setId(DRESS_TYPE_ID);
        newDressTypeDto.setName(DRESS_TYPE_NAME);

        return newDressTypeDto;
    }

    public static ModelType createNewModelType() {

        var newModelType = new ModelType();
        newModelType.setId(MODEL_TYPE_ID);
        newModelType.setName(MODEL_TYPE_NAME);

        return newModelType;
    }

    public static ModelTypeDto createNewModelTypeDto() {

        var newModelTypeDto = new ModelTypeDto();
        newModelTypeDto.setId(MODEL_TYPE_ID);
        newModelTypeDto.setName(MODEL_TYPE_NAME);

        return newModelTypeDto;
    }

    public static List<ModelType> createNewModelTypes() {
        return List.of(
                new ModelType(),
                new ModelType(),
                new ModelType()
        );
    }
}
