package com.imaginesoft.application.couture.controller.message;

import org.springframework.http.HttpStatus;

public class Error extends Response {

    public Error(HttpStatus status, String date, String message, Object... data) {
        super(status, date, message, data);
    }
}
