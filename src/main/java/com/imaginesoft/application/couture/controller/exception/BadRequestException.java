package com.imaginesoft.application.couture.controller.exception;

public class BadRequestException extends Exception {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Exception exception) {
        super(message, exception);
    }


}
