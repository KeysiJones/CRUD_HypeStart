package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class CarFipeTableResponse {

    @JsonProperty("Valor")
    @ApiModelProperty("Car price")
    private String price;

    @JsonProperty("Marca")
    @ApiModelProperty("Car brand")
    private String brand;

    @JsonProperty("Modelo")
    @ApiModelProperty("Car model")
    private String model;

    @JsonProperty("AnoModelo")
    @ApiModelProperty("Car modelYear")
    private Integer modelYear;

    @JsonProperty("Combustivel")
    @ApiModelProperty("type of fuel")
    private String fuel;

    @JsonProperty("CodigoFipe")
    @ApiModelProperty("TabelaFipe code identification")
    private String fipeCode;

    @JsonProperty("MesReferencia")
    @ApiModelProperty("reference month")
    private String referenceMonth;

    @JsonProperty("TipoVeiculo")
    @ApiModelProperty("Vehicle Type")
    private Integer vehicleType;

    @JsonProperty("SiglaCombustivel")
    @ApiModelProperty("Acronym Fuel")
    private String acronymFuel;

    public CarFipeTableResponse() {
    }

    public CarFipeTableResponse(String price, String brand, String model, Integer modelYear, String fuel, String fipeCode, String referenceMonth, Integer vehicleType, String acronymFuel) {
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.fuel = fuel;
        this.fipeCode = fipeCode;
        this.referenceMonth = referenceMonth;
        this.vehicleType = vehicleType;
        this.acronymFuel = acronymFuel;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public String getFuel() {
        return fuel;
    }

    public String getFipeCode() {
        return fipeCode;
    }

    public String getReferenceMonth() {
        return referenceMonth;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public String getAcronymFuel() {
        return acronymFuel;
    }

    @Override
    public String toString() {
        return "CarFipeTableResponse{" +
                "price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", fuel='" + fuel + '\'' +
                ", fipeCode='" + fipeCode + '\'' +
                ", referenceMonth='" + referenceMonth + '\'' +
                ", vehicleType=" + vehicleType +
                ", acronymFuel='" + acronymFuel + '\'' +
                '}';
    }
}
