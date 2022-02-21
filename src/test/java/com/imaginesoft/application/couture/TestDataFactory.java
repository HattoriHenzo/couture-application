package com.imaginesoft.application.couture;

import com.imaginesoft.application.couture.dto.ClientDto;
import com.imaginesoft.application.couture.dto.DressTypeDto;
import com.imaginesoft.application.couture.dto.MeasureTypeDto;
import com.imaginesoft.application.couture.dto.ModelTypeDto;
import com.imaginesoft.application.couture.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static final String EMPTY_STRING = "";

    public static final Long ID = 1L;
    public static final Long BAD_ID = 5L;
    public static final String BAD_URI = "/bad/uri";
    public static final String BAD_PATH_PARAM = "/\\param\\";
    public static final String BAD_BODY = "bad body";
    public static final String SUCCESS_DATE = "2022-01-04";

    public static final Long EMPLOYEE_ID = 3L;
    public static final String EMPLOYEE_FIRST_NAME = "Samuel";
    public static final String EMPLOYEE_EDITED_FIRST_NAME = "Thierry";
    public static final String EMPLOYEE_LAST_NAME = "Jeans";
    public static final String EMPLOYEE_TELEPHONE = "418-555-9999";
    public static final String EMPLOYEE_EDITED_TELEPHONE = "418-333-9988";
    public static final Gender EMPLOYEE_GENDER_MALE = Gender.MALE;

    public static final Long CLIENT_ID = 1L;
    public static final Long CLIENT_ADDED_ID = 6L;
    public static final Long CLIENT_TO_DELETE = 3L;
    public static final int NUMBER_OF_CLIENTS = 5;
    public static final String CLIENT_FIRST_NAME = "CARLOS";
    public static final String CLIENT_EDITED_FIRST_NAME = "THIERRY";
    public static final String CLIENT_LAST_NAME = "JEANS";
    public static final String CLIENT_TELEPHONE = "514-999-4800";
    public static final String CLIENT_EDITED_TELEPHONE = "418-333-9988";
    public static final Gender CLIENT_GENDER_MALE = Gender.MALE;

    public static final Long DRESS_ID = 1L;
    public static final Long DRESS_ID_TO_DELETE = 2L;
    public static final int DRESS_AMOUNT = 1000;
    public static final int DRESS_NEGATIVE_AMOUNT = -500;
    public static final int DRESS_UPDATED_AMOUNT = 1500;

    public static final Long DRESS_TYPE_ID = 1L;
    public static final Long DRESS_TYPE_TO_DELETE = 2L;
    public static final String DRESS_TYPE_NAME = "PANTS";

    public static final Long MODEL_TYPE_ID = 3L;
    public static final String MODEL_TYPE_NAME = "SHERPA";
    public static final String MODEL_TYPE_EDITED_NAME = "AFRICAN";

    public static final Long MEASURE_TYPE_ID = 3L;
    public static final Long MEASURE_TYPE_TO_DELETE = 4L;
    public static final String MEASURE_TYPE_NAME = "SHOULDER";
    public static final String MEASURE_TYPE_EDITED_NAME = "HARM";

    public static final Long MEASURE_ID = 3L;
    public static final int MEASURE_VALUE = 10;
    public static final int MEASURE_EDITED_VALUE = 12;

    public static final Long MATERIAL_TYPE_ID = 3L;
    public static final String MATERIAL_TYPE_NAME = "COTTON";
    public static final String MATERIAL_TYPE_EDITED_NAME = "NYLON";
    public static final String MATERIAL_TYPE_IMAGE = "image_path";
    public static final String MATERIAL_TYPE_EDITED_IMAGE = "image_path_edit";

    public static final Long ORDER_ID = 3L;
    public static final String ORDER_NUMBER = "0001";
    public static final String ORDER_EDITED_NUMBER = "0002";
    public static final LocalDateTime ORDER_DATE = LocalDateTime.now();
    public static final LocalDateTime ORDER_DELIVERY_DATE = LocalDateTime.now();

    public static final Long LOGIN_ID = 1L;
    public static final String LOGIN_USERNAME = "superuser";
    public static final String LOGIN_PASSWORD = "super_password";
    public static final LoginCategory LOGIN_CATEGORY = LoginCategory.EMPLOYEE;

    public static Employee createNewEmployee() {
        var newEmployee = new Employee();
        newEmployee.setId(EMPLOYEE_ID);
        newEmployee.setFirstName(EMPLOYEE_FIRST_NAME);
        newEmployee.setLastName(EMPLOYEE_LAST_NAME);
        newEmployee.setTelephone(EMPLOYEE_TELEPHONE);
        newEmployee.setGender(EMPLOYEE_GENDER_MALE);

        return newEmployee;
    }

    public static List<Employee> createNewEmployees() {
        return List.of(
                new Employee(),
                new Employee(),
                new Employee()
        );
    }

    public static Client createNewClient() {
        var newClient = new Client();
        newClient.setId(CLIENT_ID );
        newClient.setFirstName(CLIENT_FIRST_NAME);
        newClient.setLastName(CLIENT_LAST_NAME);
        newClient.setGender(Gender.MALE);
        newClient.setTelephone(CLIENT_TELEPHONE);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        newClient.setOrders(orders);

        return newClient;
    }

    public static ClientDto createNewClientDto() {
        var newClientDto = new ClientDto();
        newClientDto.setId(CLIENT_ID );
        newClientDto.setFirstName(CLIENT_FIRST_NAME);
        newClientDto.setLastName(CLIENT_LAST_NAME);
        newClientDto.setGender(Gender.MALE.name());
        newClientDto.setTelephone(CLIENT_TELEPHONE);

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
        newDress.setId(DRESS_ID);
        newDress.setAmount(DRESS_AMOUNT);
        newDress.setDressType(new DressType());
        newDress.setModelType(new ModelType());
        newDress.setMaterialType(new MaterialType());
        newDress.setMeasures(createNewMeasures());

        return newDress;
    }

    public static List<Dress> createNewDresses() {
        return List.of(
                new Dress(),
                new Dress(),
                new Dress()
        );
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

    public static Measure createNewMeasure() {
        Measure newMeasure = new Measure();
        newMeasure.setId(MEASURE_ID);
        newMeasure.setValue(MEASURE_VALUE);
        newMeasure.setDress(new Dress());
        newMeasure.setMeasureType(new MeasureType());

        return newMeasure;
    }

    public static MeasureType createNewMeasureType() {
        MeasureType newMeasureType = new MeasureType();
        newMeasureType.setId(MEASURE_ID);
        newMeasureType.setName(MEASURE_TYPE_NAME);
        newMeasureType.setMeasures(createNewMeasures());

        return newMeasureType;
    }

    public static MeasureTypeDto createNewMeasureTypeDto() {
        var newMeasureTypeDto = new MeasureTypeDto();
        newMeasureTypeDto.setName(MEASURE_TYPE_NAME);

        return newMeasureTypeDto;
    }

    public static List<MeasureType> createNewMeasureTypes() {

        return List.of(
                new MeasureType(),
                new MeasureType(),
                new MeasureType()
        );
    }

    public static MaterialType createNewMaterialType() {
        MaterialType newMaterialType = new MaterialType();
        newMaterialType.setName(MATERIAL_TYPE_NAME);
        newMaterialType.setImage(MATERIAL_TYPE_IMAGE);
        return newMaterialType;
    }

    public static List<MaterialType> createNewMaterialTypes() {
        return List.of(
                new MaterialType(),
                new MaterialType(),
                new MaterialType()
        );
    }

    public static Order createNewOrder() {
        var newOrder = new Order();
        newOrder.setId(ORDER_ID);
        newOrder.setNumber(ORDER_NUMBER);
        newOrder.setClient(new Client());
        newOrder.setDate(ORDER_DATE);
        newOrder.setDeliveryDate(ORDER_DELIVERY_DATE);
        return newOrder;
    }

    public static List<Order> createNewOrders() {
        return List.of(
                new Order(),
                new Order(),
                new Order()
        );
    }

    public static Login createNewLogin() {
        var newLogin = new Login();
        newLogin.setId(LOGIN_ID);
        newLogin.setUsername(LOGIN_USERNAME);
        newLogin.setPassword(LOGIN_PASSWORD);
        newLogin.setLoginCategory(LOGIN_CATEGORY);
        return newLogin;
    }

    public static List<Login> createNewLogins() {
        return List.of(
                new Login(),
                new Login(),
                new Login()
        );
    }
}
