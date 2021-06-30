package com.example.demo.service.impl;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.repository.CarroRepository;
import com.example.demo.service.CarroService;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.*;
import java.util.*;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    CarroRepository repository;

    @Override
    public Mono<Carro> saveCar(Carro carro) {
        return Mono.just(repository.save(carro));
    }

    @Override
    public Flux<Carro> listAllCars() {
        return Flux.fromIterable(repository.findAll());
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> updateCar(Long id, Carro carro) {
        return repository.findById(id).map(car -> {
            car.setMarca(Optional.ofNullable(carro.getMarca()).orElse(car.getMarca()));
            car.setModelo(Optional.ofNullable(carro.getModelo()).orElse(car.getModelo()));
            car.setTipo(Optional.ofNullable(carro.getTipo()).orElse(car.getTipo()));
            car.setAnoFabricacao(Optional.ofNullable(carro.getAnoFabricacao()).orElse(car.getAnoFabricacao()));
            car.setAnoModelo(Optional.ofNullable(carro.getAnoModelo()).orElse(car.getAnoModelo()));
            car.setPreco(Optional.ofNullable(carro.getPreco()).orElse(car.getPreco()));
            car.setQuantidade(Optional.ofNullable(carro.getQuantidade()).orElse(car.getQuantidade()));
            repository.save(car);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com ID " + id + " atualizado com sucesso"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum registro com o ID " + id + " foi encontrado"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> deleteCarById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com o ID " + id + " deletado com sucesso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Registro com o ID " + id + " não foi encontrado"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findCarById(Long id) {
        Optional<Carro> carro = repository.findById(id);
        if (carro.isPresent()) {
            return new ResponseEntity<>(carro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o ID " + id + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByTipo(TipoCarro tipoCarro) {
        Optional<List<Carro>> carros = repository.findAllCarsByTipo(tipoCarro);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o tipo " + tipoCarro + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByMarca(String marca) {
        Optional<List<Carro>> carros = repository.findAllCarsByMarcaIgnoreCaseContaining(marca);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro do fabricante " + marca + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByModelo(String modelo) {
        Optional<List<Carro>> carros = repository.findAllCarsByModeloIgnoreCaseContaining(modelo);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro do modelo " + modelo + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByAnoFabricacao(Integer anoFabricacao) {
        Optional<List<Carro>> carros = repository.findAllCarsByAnoFabricacao(anoFabricacao);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro fabricado em " + anoFabricacao + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByAnoModelo(Integer anoModelo) {
        Optional<List<Carro>> carros = repository.findAllCarsByAnoModelo(anoModelo);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro do ano/modelo " + anoModelo + " foi encontrado."), HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<?> findAllCarsByPreco(Double preco) {
        Optional<List<Carro>> carros = repository.findAllCarsByPreco(preco);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o valor " + preco + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByQuantidade(Integer quantidade) {
        Optional<List<Carro>> carros = repository.findAllCarsByQuantidade(quantidade);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro foi encontrado com essa quantidade em estoque."), HttpStatus.NOT_FOUND);
    }

}
