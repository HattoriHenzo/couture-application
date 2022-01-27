package com.imaginesoft.application.couture.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperWrapper implements MapperWrapper {

    private ModelMapper mapper;

    public ModelMapperWrapper() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <T> T performMapping(Object source, Class<T> destination) {
        return mapper.map(source, destination);
    }
}
