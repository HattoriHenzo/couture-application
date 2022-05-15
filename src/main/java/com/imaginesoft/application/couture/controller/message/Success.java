package com.imaginesoft.application.couture.controller.message;

import org.springframework.http.HttpStatus;

public class Success extends Response {

    public Success() {
        super();
    }

    public Success(HttpStatus status, String date, String message, Object... data) {
        super(status, date, message, data);
    }
}
