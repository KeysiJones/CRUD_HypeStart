package com.example.demo.service;

import com.example.demo.client.CarBrandResponse;
import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

    Mono<Car> saveCar(Car car);

    Flux<Car> listAllCars();

    ResponseEntity<MyCustomHttpResponse> updateCar(Long Id, Car car);

    ResponseEntity<MyCustomHttpResponse> deleteCarById(Long id);

    ResponseEntity<?> findCarById(Long id);

    ResponseEntity<?> findAllCarsByType(CarType carType);

    ResponseEntity<?> findAllCarsByBrand(String brand);

    ResponseEntity<?> findAllCarsByModel(String model);

    ResponseEntity<?> findAllCarsByManufacturingYear(Integer yearManuf);

    ResponseEntity<?> findAllCarsByModelYear(Integer yearModel);

    ResponseEntity<?> findAllCarsByPrice(Double price);

    ResponseEntity<?> findAllCarsByQuantity(Integer quantity);

    Flux<CarBrandResponse> retrieveAllCarBrands();

    Mono<String> retrievePrice(String brandCode, String modelCode, String yearId);
}

