package com.example.demo.model;

import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o nome do cliente!")
    private String nome;

    @NotBlank(message = "Favor informar um CPF válido!")
    @CPF
    private String cpf;

    @NotBlank(message = "Favor informar um e-mail válido!")
    @Email
    private String email;

    @NotBlank(message = "Favor informar um telefone válido!")
    private String telefone;

    @OneToMany
    @JoinColumn(name = "carro_id")
    private List<Carro> carrosCliente;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String email, String telefone, List<Carro> carrosCliente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.carrosCliente = carrosCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Carro> getCarrosCliente() {
        return carrosCliente;
    }

    public void setCarrosCliente(List<Carro> carrosCliente) {
        this.carrosCliente = carrosCliente;
    }

}
