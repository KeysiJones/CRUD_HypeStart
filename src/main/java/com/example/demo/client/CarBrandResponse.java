package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class CarBrandResponse {


    @JsonProperty("nome")
    @ApiModelProperty("cars brand")
    private String brand;

    @JsonProperty("codigo")
    @ApiModelProperty("brand code")
    private String code;

    public CarBrandResponse(String brand, String code) {
        this.brand = brand;
        this.code = code;
    }

    public CarBrandResponse() {
    }

    public String getBrand() {
        return brand;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "CarBrands(brand=" + this.getBrand() + ", code=" + this.getCode() + ")";
    }

}

