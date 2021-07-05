package com.example.demo.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarTabelaFipeResponse {

    @JsonProperty("Valor")
    private String price;

    @JsonProperty("Marca")
    private String brand;

    @JsonProperty("Modelo")
    private String model;

    @JsonProperty("AnoModelo")
    private Integer modelYear;

    @JsonProperty("Combustivel")
    private String fuel;

    @JsonProperty("CodigoFipe")
    private String fipeCode;

    @JsonProperty("MesReferencia")
    private String referenceMonth;

    @JsonProperty("TipoVeiculo")
    private Integer vehicleType;

    @JsonProperty("SiglaCombustivel")
    private String acronymFuel;


    public CarTabelaFipeResponse() {
    }

    public CarTabelaFipeResponse(String price, String brand, String model, Integer modelYear, String fuel, String fipeCode, String referenceMonth, Integer vehicleType, String acronymFuel) {
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
        return "CarTabelaFipeResponse{" +
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
