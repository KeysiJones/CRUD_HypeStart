package com.example.demo.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please, enter the full name.")
    private String name;

    @NotBlank(message = "Please, enter the registration number.")
    private String registration;

    @NotBlank(message = "Please, enter a valid CPF.")
    @CPF
    private String cpf;

    @NotBlank(message = "Please, enter a valid e-mail.")
    @Email
    private String email;

    public Vendor(String name, String registration, String cpf, String email){
        this.id = id;
        this.name = name;
        this.registration = registration;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getRegistration() { return registration; }

    public void setRegistration(String registration) { this.registration = registration; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
