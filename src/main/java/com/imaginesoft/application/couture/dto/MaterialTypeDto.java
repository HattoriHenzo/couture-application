package com.imaginesoft.application.couture.dto;

import com.imaginesoft.application.couture.dto.generic.GenericTypeDto;

public class MaterialTypeDto extends GenericTypeDto {

    private String image;

    public MaterialTypeDto() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
