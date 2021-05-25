package com.example.demo.model;

//import javax.persistence.*;

@Entity
public class Carro {

    @Id
    private Long id;
    private String marca;
    private String modelo;
    //@Enumerated (EnumType.STRING)
    private TipoCarro tipo;


    public Carro(Long id, String marca, String modelo, TipoCarro tipo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Carro(){

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
}
