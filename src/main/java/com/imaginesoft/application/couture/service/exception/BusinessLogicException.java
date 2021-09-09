package com.imaginesoft.application.couture.service.exception;

public class BusinessLogicException extends Exception {

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String message, Exception exception) {
        super(message, exception);
    }


}
