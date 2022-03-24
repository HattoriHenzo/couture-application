package com.imaginesoft.application.couture.dto;

public class LoginDto {

    private Long id;
    private String username;
    private String password;
    private String employeeFirstName;
    private String employeeLastName;
    private String loginCategory;

    public LoginDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getLoginCategory() {
        return loginCategory;
    }

    public void setLoginCategory(String loginCategory) {
        this.loginCategory = loginCategory;
    }
}
