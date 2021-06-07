package com.example.demo.model;

import com.example.demo.enums.TipoCarro;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe a marca do carro !")
    private String marca;

    @NotBlank(message = "Informe o modelo do carro !")
    private String modelo;

    @Enumerated(EnumType.STRING)
    private TipoCarro tipo;

    public Carro(String marca, String modelo, TipoCarro tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Carro() {
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public TipoCarro getTipo() {
        return tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipo(TipoCarro tipo) {
        this.tipo = tipo;
    }
}