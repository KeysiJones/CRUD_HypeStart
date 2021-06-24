package com.example.demo.model;

import com.example.demo.enums.FormaPagamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Informe os itens do pedido.")
    @OneToMany
    private List<Carro> listaCarros;

    @NotNull(message = "Informe o valor total do pedido.")
    private Double valorTotal;

    @NotNull(message = "Informe a forma de pagamento.")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public Pedido() {
    }

    public Pedido(Long id, List<Carro> listaCarros, Double valorTotal, FormaPagamento formaPagamento) {
        this.id = id;
        this.listaCarros = listaCarros;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Carro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carro> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
