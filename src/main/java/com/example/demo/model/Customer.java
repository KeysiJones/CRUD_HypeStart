package com.example.demo.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please, enter the full name.")
    private String name;

    @NotBlank(message = "Please, enter a valid CPF.")
    @CPF
    private String cpf;

    @NotBlank(message = "Please, enter a valid e-mail.")
    @Email
    private String email;

    @NotBlank(message = "Please, enter a valid phone number.")
    private String phone;

    @OneToMany
    @JoinColumn(name = "car_id")
    private List<Car> carsCostumer;

    public Customer() {
    }

    public Customer(Long id, String name, String cpf, String email, String phone, List<Car> carsCostumer) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.carsCostumer = carsCostumer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Car> getCarsCostumer() {
        return carsCostumer;
    }

    public void setCarsCostumer(List<Car> carsCostumer) {
        this.carsCostumer = carsCostumer;
    }

}
