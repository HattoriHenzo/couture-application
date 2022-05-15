package com.imaginesoft.application.couture;

import com.imaginesoft.application.couture.configuration.security.dto.LoginDto;
import com.imaginesoft.application.couture.configuration.security.model.Login;
import com.imaginesoft.application.couture.configuration.security.model.LoginRole;
import com.imaginesoft.application.couture.dto.*;
import com.imaginesoft.application.couture.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String EMPTY_STRING = "";

    public static final Long ID = 1L;
    public static final String BAD_URI = "/bad/uri";
    public static final String BAD_PATH_PARAM = "param";
    public static final String BAD_BODY = "bad body";
    public static final String SUCCESS_DATE = "2022-01-04";

    public static final Long EMPLOYEE_ID = 3L;
    public static final Long EMPLOYEE_TO_DELETE = 1L;
    public static final String EMPLOYEE_FIRST_NAME = "JEANNE";
    public static final String EMPLOYEE_EDITED_FIRST_NAME = "GERALD";
    public static final String EMPLOYEE_LAST_NAME = "SOSSOU";
    public static final String EMPLOYEE_TELEPHONE = "99110952";
    public static final String EMPLOYEE_EDITED_TELEPHONE = "99110854";
    public static final Gender EMPLOYEE_GENDER_MALE = Gender.FEMALE;

    public static final Long CLIENT_ID = 1L;
    public static final Long CLIENT_TO_DELETE = 3L;
    public static final String CLIENT_FIRST_NAME = "EMMANUEL";
    public static final String CLIENT_EDITED_FIRST_NAME = "THIERRY";
    public static final String CLIENT_LAST_NAME = "JEANS";
    public static final String CLIENT_TELEPHONE = "418-888-9988";
    public static final String CLIENT_EDITED_TELEPHONE = "418-333-9988";
    public static final Gender CLIENT_GENDER_MALE = Gender.MALE;

    public static final Long DRESS_ID = 1L;
    public static final Long DRESS_TO_DELETE = 4L;
    public static final int DRESS_AMOUNT = 500;
    public static final int DRESS_NEGATIVE_AMOUNT = -500;
    public static final int DRESS_EDITED_AMOUNT = 1500;

    public static final Long DRESS_TYPE_ID = 1L;
    public static final Long DRESS_TYPE_TO_DELETE = 3L;
    public static final String DRESS_TYPE_NAME = "PANTS";
    public static final String DRESS_TYPE_EDITED_NAME = "T-SHIRT";

    public static final Long MODEL_TYPE_ID = 3L;
    public static final Long MODEL_TYPE_TO_DELETE = 4L;
    public static final String MODEL_TYPE_NAME = "SWEAT";
    public static final String MODEL_TYPE_EDITED_NAME = "AFRICAN";

    public static final Long MEASURE_TYPE_ID = 3L;
    public static final Long MEASURE_TYPE_TO_DELETE = 4L;
    public static final String MEASURE_TYPE_NAME = "HAND";
    public static final String MEASURE_TYPE_EDITED_NAME = "HARM";

    public static final Long MEASURE_ID = 1L;
    public static final Long MEASURE_TO_DELETE = 2L;
    public static final int MEASURE_VALUE = 5;
    public static final int MEASURE_EDITED_VALUE = 12;

    public static final Long MATERIAL_TYPE_ID = 3L;
    public static final String MATERIAL_TYPE_NAME = "SILK";
    public static final String MATERIAL_TYPE_EDITED_NAME = "NYLON";
    public static final String MATERIAL_TYPE_IMAGE = "/image/path";
    public static final String MATERIAL_TYPE_EDITED_IMAGE = "/image/path/edited";

    public static final Long ORDER_ID = 1L;
    public static final Long ORDER_TO_DELETE = 2L;
    public static final String ORDER_NUMBER = "order-00001";
    public static final String ORDER_EDITED_NUMBER = "order-00002";
    public static final LocalDateTime ORDER_DATE = LocalDateTime.parse("2021-09-20 18:00:00", DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    public static final LocalDateTime ORDER_DELIVERY_DATE = LocalDateTime.parse("2021-09-30 18:00:00", DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));

    public static final Long LOGIN_ID = 1L;
    public static final Long LOGIN_TO_DELETE = 2L;
    public static final String LOGIN_USERNAME = "username_1";
    public static final String LOGIN_PASSWORD = "password_1";
    public static final LoginRole LOGIN_CATEGORY = LoginRole.ADMIN;
    public static final String LOGIN_EDITED_USERNAME = "username_5";
    public static final String LOGIN_EDITED_PASSWORD = "password_5";

    // models
    public static Employee createNewEmployee() {
        var newEmployee = new Employee();
        newEmployee.setId(EMPLOYEE_ID);
        newEmployee.setFirstName(EMPLOYEE_FIRST_NAME);
        newEmployee.setLastName(EMPLOYEE_LAST_NAME);
        newEmployee.setTelephone(EMPLOYEE_TELEPHONE);
        newEmployee.setGender(EMPLOYEE_GENDER_MALE);
        newEmployee.setLogin(createNewLogin());

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
        var orders = new ArrayList<Order>();
        orders.add(createNewOrder());
        newClient.setOrders(orders);

        return newClient;
    }

    public static List<Client> createNewClients() {
        return List.of(new Client(), new Client());
    }

    public static Dress createNewDress() {
        var newDress = new Dress();
        newDress.setId(DRESS_ID);
        newDress.setAmount(DRESS_AMOUNT);
        newDress.setDressType(createNewDressType());
        newDress.setMaterialType(createNewMaterialType());
        newDress.setModelType(createNewModelType());
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
        var measures = new ArrayList<Measure>();
        measures.add(createNewMeasure());

        return measures;
    }

    public static DressType createNewDressType() {
        var newDressType = new DressType();
        newDressType.setId(DRESS_TYPE_ID);
        newDressType.setName(DRESS_TYPE_NAME);
        newDressType.setDresses(createNewDresses());

        return newDressType;
    }

    public static List<DressType> createNewDressTypes() {
        return List.of(
                new DressType(),
                new DressType(),
                new DressType()
        );
    }

    public static ModelType createNewModelType() {
        var newModelType = new ModelType();
        newModelType.setId(MODEL_TYPE_ID);
        newModelType.setName(MODEL_TYPE_NAME);

        return newModelType;
    }

    public static List<ModelType> createNewModelTypes() {
        return List.of(
                new ModelType(),
                new ModelType(),
                new ModelType()
        );
    }

    public static Measure createNewMeasure() {
        var newMeasure = new Measure();
        newMeasure.setId(MEASURE_ID);
        newMeasure.setValue(MEASURE_VALUE);

        return newMeasure;
    }

    public static MeasureType createNewMeasureType() {
        var newMeasureType = new MeasureType();
        newMeasureType.setId(MEASURE_TYPE_ID);
        newMeasureType.setName(MEASURE_TYPE_NAME);
        newMeasureType.setMeasures(createNewMeasures());

        return newMeasureType;
    }

    public static List<MeasureType> createNewMeasureTypes() {
        return List.of(createNewMeasureType());
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

    public static Login createNewLogin() {
        var newLogin = new Login();
        newLogin.setId(LOGIN_ID);
        newLogin.setUsername(LOGIN_USERNAME);
        newLogin.setPassword(LOGIN_PASSWORD);
        newLogin.setLoginCategory(LOGIN_CATEGORY);
        newLogin.setCredentialsNonExpired(true);
        newLogin.setAccountNonExpired(true);
        newLogin.setAccountNonLocked(true);
        newLogin.setEnabled(true);
        return newLogin;
    }

    public static List<Login> createNewLogins() {
        return List.of(
                new Login(),
                new Login(),
                new Login()
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

    // dtos
    public static EmployeeDto createNewEmployeeDto() {
        var newEmployeeDto = new EmployeeDto();
        newEmployeeDto.setId(EMPLOYEE_ID);
        newEmployeeDto.setFirstName(EMPLOYEE_FIRST_NAME);
        newEmployeeDto.setLastName(EMPLOYEE_LAST_NAME);
        newEmployeeDto.setTelephone(EMPLOYEE_TELEPHONE);
        newEmployeeDto.setGender(EMPLOYEE_GENDER_MALE.name());
        var loginDto = createNewLoginDto();
        loginDto.setId(3L);
        loginDto.setUsername("username_3");
        loginDto.setPassword("password_3");
        newEmployeeDto.setLogin(loginDto);

        return newEmployeeDto;
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

    public static List<ClientDto> createNewClientDtos() {
        return List.of(new ClientDto(), new ClientDto());
    }

    public static DressDto createNewDressDto() {
        var newDressDto = new DressDto();
        newDressDto.setId(DRESS_ID);
        newDressDto.setAmount(DRESS_AMOUNT);
        newDressDto.setMeasures(List.of(createNewMeasureDto()));
        newDressDto.setDressType(createNewDressTypeDto());
        newDressDto.setModelType(createNewModelTypeDto());
        newDressDto.setMaterialType(createNewMaterialTypeDto());

        return newDressDto;
    }

    public static DressTypeDto createNewDressTypeDto() {
        var newDressTypeDto = new DressTypeDto();
        newDressTypeDto.setId(DRESS_TYPE_ID);
        newDressTypeDto.setName(DRESS_TYPE_NAME);

        return newDressTypeDto;
    }

    public static ModelTypeDto createNewModelTypeDto() {
        var newModelTypeDto = new ModelTypeDto();
        newModelTypeDto.setId(MODEL_TYPE_ID);
        newModelTypeDto.setName(MODEL_TYPE_NAME);

        return newModelTypeDto;
    }

    public static MeasureDto createNewMeasureDto() {
        var newMeasureDto = new MeasureDto();
        newMeasureDto.setId(MEASURE_ID);
        newMeasureDto.setValue(MEASURE_VALUE);
        newMeasureDto.setMeasureType(createNewMeasureTypeDto());

        return newMeasureDto;
    }

    public static MeasureTypeDto createNewMeasureTypeDto() {
        var newMeasureTypeDto = new MeasureTypeDto();
        newMeasureTypeDto.setId(MEASURE_TYPE_ID);
        newMeasureTypeDto.setName(MEASURE_TYPE_NAME);

        return newMeasureTypeDto;
    }

    public static MaterialTypeDto createNewMaterialTypeDto() {
        var newMaterialTypeDto = new MaterialTypeDto();
        newMaterialTypeDto.setId(MATERIAL_TYPE_ID);
        newMaterialTypeDto.setName(MATERIAL_TYPE_NAME);
        newMaterialTypeDto.setImage(MATERIAL_TYPE_IMAGE);

        return newMaterialTypeDto;
    }

    public static OrderDto createNewOrderDto() {
        var newOrderDto = new OrderDto();
        newOrderDto.setId(ORDER_ID);
        newOrderDto.setNumber(ORDER_NUMBER);
        newOrderDto.setClientFirstName(CLIENT_FIRST_NAME);
        newOrderDto.setClientLastName(CLIENT_LAST_NAME);
        newOrderDto.setDate(ORDER_DATE.format(DateTimeFormatter.ISO_DATE_TIME));
        newOrderDto.setDeliveryDate(ORDER_DELIVERY_DATE.format(DateTimeFormatter.ISO_DATE_TIME));

        return newOrderDto;
    }

    public static LoginDto createNewLoginDto() {
        var newLoginDto = new LoginDto();
        newLoginDto.setId(LOGIN_ID);
        newLoginDto.setEmployeeFirstName(EMPLOYEE_FIRST_NAME);
        newLoginDto.setEmployeeLastName(EMPLOYEE_LAST_NAME);
        newLoginDto.setUsername(LOGIN_USERNAME);
        newLoginDto.setPassword(LOGIN_PASSWORD);
        newLoginDto.setLoginCategory(LOGIN_CATEGORY.name());
        newLoginDto.setCredentialsNonExpired(true);
        newLoginDto.setAccountNonExpired(true);
        newLoginDto.setAccountNonLocked(true);
        newLoginDto.setEnabled(true);

        return newLoginDto;
    }
}
