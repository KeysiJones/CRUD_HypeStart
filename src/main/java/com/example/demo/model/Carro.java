package com.example.demo.model;

import com.example.demo.enums.TipoCarro;
import javax.persistence.*;

@Entity
public class Carro {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) (id autoincrement p/ Post)
    private Long id;
    private String marca;
    private String modelo;
    private TipoCarro tipo;

    public Carro(Long id, String marca, String modelo, TipoCarro tipo) {
        this.id = id;
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
