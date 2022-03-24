package com.imaginesoft.application.couture.controller;

import com.imaginesoft.application.couture.dto.EmployeeDto;
import com.imaginesoft.application.couture.model.Employee;
import com.imaginesoft.application.couture.service.EmployeeService;
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

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest extends BaseControllerTest {

    @MockBean
    private EmployeeService service;

    @BeforeEach
    void setUp() {

    }

    @Test
    void givenId_whenCallFindById_thenReturns_200_OK() throws Exception {
        var employee = createNewEmployee();
        var employeeResponse = createNewEmployeeDto();

        when(service.findById(anyLong())).thenReturn(employee);
        when(mapper.performMapping(employee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/employees/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.date", is(SUCCESS_DATE)))
                .andExpect(jsonPath("$.data", hasSize(1)));
    }

    @Test
    void givenId_whenCallFindById_thenReturns_400_BAD_REQUEST() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Employee());

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/employees/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenId_whenCallFindById_thenReturns_404_NOT_FOUND() throws Exception {
        when(service.findById(anyLong())).thenReturn(new Employee());

        mockMvc.perform(get(BAD_URI, ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenAll_whenCallFindAll_thenReturns_200_OK() throws Exception {
        var employee = createNewEmployee();
        var employees = createNewEmployees();
        var employeeResponse = createNewEmployeeDto();

        when(service.findAll()).thenReturn(employees);
        when(mapper.performMapping(employee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(get(ApplicationDataFactory.API_V1 + "/employees")
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
        when(service.findAll()).thenReturn(createNewEmployees());

        mockMvc.perform(get(BAD_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenEmployee_whenCallCreate_thenReturns_400_BAD_REQUEST() throws Exception {
        var employeeRequest = BAD_BODY;
        var employeeToCreate = createNewEmployee();
        var createdEmployee = createNewEmployee();
        var employeeResponse = createNewEmployeeDto();

        when(mapper.performMapping(employeeRequest, Employee.class)).thenReturn(employeeToCreate);
        when(service.createOrUpdate(employeeToCreate)).thenReturn(createdEmployee);
        when(mapper.performMapping(createdEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmployee_whenCallCreate_thenReturns_200_OK() throws Exception {
        var employeeRequest = createNewEmployeeDto();
        var employeeToCreate = createNewEmployee();
        var createdEmployee = createNewEmployee();
        var employeeResponse = createNewEmployeeDto();

        when(mapper.performMapping(employeeRequest, Employee.class)).thenReturn(employeeToCreate);
        when(service.createOrUpdate(employeeToCreate)).thenReturn(createdEmployee);
        when(mapper.performMapping(createdEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(post(ApplicationDataFactory.API_V1 + "/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("created")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenEmployee_whenCallUpdate_thenReturns_200_OK() throws Exception {
        var employeeRequest = createNewEmployeeDto();
        var employeeToCreate = createNewEmployee();
        var createdEmployee = createNewEmployee();
        var employeeResponse = createNewEmployeeDto();

        when(mapper.performMapping(employeeRequest, Employee.class)).thenReturn(employeeToCreate);
        when(service.createOrUpdate(employeeToCreate)).thenReturn(createdEmployee);
        when(mapper.performMapping(createdEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.status", is("OK")),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("updated")),
                        jsonPath("$.data", hasSize(1))
                );
    }

    @Test
    void givenEmployee_whenCallUpdate_thenReturns_400_BAD_REQUEST() throws Exception {
        var employeeRequest = BAD_BODY;
        var employeeToCreate = createNewEmployee();
        var createdEmployee = createNewEmployee();
        var employeeResponse = createNewEmployeeDto();

        when(mapper.performMapping(employeeRequest, Employee.class)).thenReturn(employeeToCreate);
        when(service.createOrUpdate(employeeToCreate)).thenReturn(createdEmployee);
        when(mapper.performMapping(createdEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(put(ApplicationDataFactory.API_V1 + "/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmployee_whenCallDelete_thenReturns_200_OK() throws Exception {
        var employeeToDelete = createNewEmployee();
        var deletedEmployee = employeeToDelete;
        var employeeResponse = createNewEmployeeDto();

        when(service.findById(ID)).thenReturn(employeeToDelete);
        when(service.delete(employeeToDelete.getId())).thenReturn(deletedEmployee);
        when(mapper.performMapping(deletedEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/employees/{id}", ID)
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
    void givenEmployee_whenCallDelete_thenReturns_400_BAD_REQUEST() throws Exception {
        var employeeToDelete = createNewEmployee();
        var deletedEmployee = employeeToDelete;
        var employeeResponse = createNewEmployeeDto();

        when(service.findById(ID)).thenReturn(employeeToDelete);
        when(service.delete(employeeToDelete.getId())).thenReturn(deletedEmployee);
        when(mapper.performMapping(deletedEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/employees/ID", BAD_PATH_PARAM)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmployee_whenCallDelete_thenReturns_404_NOT_FOUND() throws Exception {
        var employeeToDelete = createNewEmployee();
        var deletedEmployee = employeeToDelete;
        var employeeResponse = createNewEmployeeDto();

        when(service.findById(ID)).thenReturn(null);
        when(service.delete(employeeToDelete.getId())).thenReturn(deletedEmployee);
        when(mapper.performMapping(deletedEmployee, EmployeeDto.class)).thenReturn(employeeResponse);
        when(dateTime.getCurrentDateTime(any(Clock.class))).thenReturn(SUCCESS_DATE);

        mockMvc.perform(delete(ApplicationDataFactory.API_V1 + "/employees/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isNotFound(),
                        jsonPath("$.date", is(SUCCESS_DATE)),
                        jsonPath("$.message", containsString("not found"))
                );
    }
}
