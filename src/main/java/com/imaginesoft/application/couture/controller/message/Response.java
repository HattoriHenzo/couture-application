package com.imaginesoft.application.couture.controller.message;

import org.springframework.http.HttpStatus;

public abstract class Response {

    private HttpStatus status;
    private String date;
    private String message;
    private Object[] data;

    public Response(HttpStatus status, String date, String message, Object... data) {
        this.status = status;
        this.date = date;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
