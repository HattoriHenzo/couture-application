package com.imaginesoft.application.couture.controller.exception;

import com.imaginesoft.application.couture.controller.message.Error;
import com.imaginesoft.application.couture.service.validator.field.DomainRulesException;
import com.imaginesoft.application.couture.util.DateTimeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private DateTimeWrapper dateTimeWrapper;

    @Autowired
    public RestControllerExceptionHandler(DateTimeWrapper dateTimeWrapper) {
        this.dateTimeWrapper = dateTimeWrapper;
    }

    @ExceptionHandler(DomainRulesException.class)
    public ResponseEntity<Error> handleDomainRulesException(DomainRulesException exception) {
        var error = new Error(HttpStatus.BAD_REQUEST,
                dateTimeWrapper.getCurrentDateTime(Clock.systemDefaultZone()),
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException exception) {
        var error = new Error(HttpStatus.BAD_REQUEST,
                dateTimeWrapper.getCurrentDateTime(Clock.systemDefaultZone()),
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Error> handleRecordNotFoundException(RecordNotFoundException exception) {
        var error = new Error(HttpStatus.NOT_FOUND,
                dateTimeWrapper.getCurrentDateTime(Clock.systemDefaultZone()),
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
