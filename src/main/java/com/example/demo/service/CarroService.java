package com.example.demo.service;

import com.example.demo.model.Carro;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CarroService {

    Mono<Carro> saveCar(Carro carro);

    Flux<List<Carro>> listAllCars();

    Mono<Carro> updateCar(Long Id);

    Mono<Carro> deleteCarById(Long id);

}
