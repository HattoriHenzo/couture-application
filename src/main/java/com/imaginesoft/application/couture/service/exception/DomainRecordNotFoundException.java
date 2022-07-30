package com.imaginesoft.application.couture.service.exception;

public class DomainRecordNotFoundException extends RuntimeException {

    public DomainRecordNotFoundException() {

    }

    public DomainRecordNotFoundException(String message) {
        super(message);
    }

    public DomainRecordNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
