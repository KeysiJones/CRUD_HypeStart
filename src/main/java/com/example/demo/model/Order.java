package com.example.demo.model;

import com.example.demo.enums.PaymentMethod;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please, enter the order items.")
    @OneToMany
    private List<Car> listCars;

    @NotNull(message = "Please, enter the total value.")
    private Double totalValue;

    @NotNull(message = "Please, enter a payment method.")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public Order() {
    }

    public Order(Long id, List<Car> listaCars, Double totalValue, PaymentMethod paymentMethod) {
        this.id = id;
        this.listCars = listaCars;
        this.totalValue = totalValue;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Car> getListCars() {
        return listCars;
    }

    public void setListCars(List<Car> listaCars) {
        this.listCars = listaCars;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
