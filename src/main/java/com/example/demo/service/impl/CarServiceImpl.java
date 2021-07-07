package com.example.demo.service.impl;

import com.example.demo.client.CarBrandResponse;
import com.example.demo.client.FipeTableClient;
import com.example.demo.controller.CarController;
import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import com.example.demo.util.MyCustomHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    CarRepository repository;

    @Autowired
    FipeTableClient fipeTableClient;

    @Override
    public Mono<Car> saveCar(Car car) {
        return Mono.just(repository.save(car));
    }

    @Override
    public Flux<Car> listAllCars() {
        return Flux.fromIterable(repository.findAll());
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> updateCar(Long id, Car vehicle) {
        return repository.findById(id).map(car -> {
            car.setBrand(Optional.ofNullable(vehicle.getBrand()).orElse(car.getBrand()));
            car.setModel(Optional.ofNullable(vehicle.getModel()).orElse(car.getModel()));
            car.setType(Optional.ofNullable(vehicle.getType()).orElse(car.getType()));
            car.setYearManuf(Optional.ofNullable(vehicle.getYearManuf()).orElse(car.getYearManuf()));
            car.setYearModel(Optional.ofNullable(vehicle.getYearModel()).orElse(car.getYearModel()));
            car.setPrice(Optional.ofNullable(vehicle.getPrice()).orElse(car.getPrice()));
            car.setQuantity(Optional.ofNullable(vehicle.getQuantity()).orElse(car.getQuantity()));
            repository.save(car);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "ID " + id + " successfully updated."), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No records with ID " + id + " were found."), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> deleteCarById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "ID " + id + " successfully deleted."), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No records with ID " + id + " were found."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findCarById(Long id) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No records with ID " + id + " were found."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByType(CarType carType) {
        Optional<List<Car>> cars = repository.findAllCarsByType(carType);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars were found with the type " + carType), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByBrand(String brand) {
        Optional<List<Car>> cars = repository.findAllCarsByBrandIgnoreCaseContaining(brand);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars were found with the brand " + brand), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByModel(String model) {
        Optional<List<Car>> cars = repository.findAllCarsByModelIgnoreCaseContaining(model);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars with the model " + model + " were found."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByManufacturingYear(Integer yearManuf) {
        Optional<List<Car>> cars = repository.findAllCarsByYearManuf(yearManuf);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars manufactured in" + yearManuf + " were found."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByModelYear(Integer yearModel) {
        Optional<List<Car>> cars = repository.findAllCarsByYearModel(yearModel);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars were found with this model year " + yearModel), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByPrice(Double price) {
        Optional<List<Car>> cars = repository.findAllCarsByPrice(price);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars with value with value " + price), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByQuantity(Integer quantity) {
        Optional<List<Car>> cars = repository.findAllCarsByQuantity(quantity);
        if (!cars.get().isEmpty()) {
            return new ResponseEntity<>(cars.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No cars were found with this quantity in stock."), HttpStatus.NOT_FOUND);
    }

    @Override
    public Flux<CarBrandResponse> retrieveAllCarBrands() {
        return fipeTableClient.retrieveAllCarBrands();
    }

    @Override
    public Mono<String> retrievePrice(String brandCode, String modelCode, String yearId) {
        LOGGER.info("Return a car with price with parameters brand {}, model {}, year {}", brandCode, modelCode, yearId);
        return fipeTableClient.retrievePrice(brandCode, modelCode, yearId);
    }
}
