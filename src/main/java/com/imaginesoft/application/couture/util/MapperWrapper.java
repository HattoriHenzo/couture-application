package com.imaginesoft.application.couture.util;

public interface MapperWrapper {

    <T> T performMapping(Object source, Class<T> destination);
}
