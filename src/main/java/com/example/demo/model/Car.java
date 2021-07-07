package com.example.demo.model;

import com.example.demo.enums.CarType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please, enter the brand.")
    private String brand;

    @NotBlank(message = "Please, enter the model.")
    private String model;

    @NotNull(message = "Please, enter the type.")
    @Enumerated(EnumType.STRING)
    private CarType type;

    @NotNull(message = "Please, enter the manufacturing year.")
    @Size(min=4, max=4)
    private Integer yearManuf;

    @NotNull(message = "Please, enter the model year.")
    @Size(min=4, max=4)
    private Integer yearModel;

    @NotNull(message = "Please, enter the price.")
    private double price;

    @NotNull(message = "Please, enter the quantity in stock.")
    private Integer quantity;

    public Car(String brand, String model, CarType type, Integer yearManuf, Integer yearModel, double price, Integer quantity) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.yearManuf = yearManuf;
        this.yearModel = yearModel;
        this.price = price;
        this.quantity = quantity;
    }

    public Car() {
    }

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public double getPrice() { return price; }

    public Integer getQuantity() { return quantity; }

    public Integer getYearManuf() { return yearManuf; }

    public Integer getYearModel() { return yearModel; }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setPrice(double price) { this.price = price; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setYearManuf(Integer yearManuf) { this.yearManuf = yearManuf; }

    public void setYearModel(Integer yearModel) { this.yearModel = yearModel; }
}