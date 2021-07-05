package com.example.demo.service;

import com.example.demo.client.CarBrandResponse;
import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface CarroService {

    Mono<Carro> saveCar(Carro carro);

    Flux<Carro> listAllCars();

    ResponseEntity<MyCustomHttpResponse> updateCar(Long Id, Carro carro);

    ResponseEntity<MyCustomHttpResponse> deleteCarById(Long id);

    ResponseEntity<?> findCarById(Long id);

    ResponseEntity<?> findAllCarsByTipo(TipoCarro tipoCarro);

    ResponseEntity<?> findAllCarsByMarca(String marca);

    ResponseEntity<?> findAllCarsByModelo(String modelo);

    ResponseEntity<?> findAllCarsByAnoFabricacao(Integer anoFaricacao);

    ResponseEntity<?> findAllCarsByAnoModelo(Integer anoModelo);

    ResponseEntity<?> findAllCarsByPreco(Double preco);

    ResponseEntity<?> findAllCarsByQuantidade(Integer quantidade);

    Flux<CarBrandResponse> retrieveAllCarBrands();

    Mono<String> retrieveCarWithPrice(String brandCode, String modelCode, String yearId);

}

