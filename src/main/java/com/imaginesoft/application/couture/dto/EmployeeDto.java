package com.imaginesoft.application.couture.dto;

import com.imaginesoft.application.couture.dto.generic.GenericPersonDto;

public class EmployeeDto extends GenericPersonDto {
    public EmployeeDto() {
        super();
    }

    private LoginDto login;

    public LoginDto getLogin() {
        return login;
    }

    public void setLogin(LoginDto login) {
        this.login = login;
    }
}
