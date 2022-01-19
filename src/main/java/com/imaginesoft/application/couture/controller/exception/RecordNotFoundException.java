package com.imaginesoft.application.couture.controller.exception;

public class RecordNotFoundException extends BadRequestException {

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
