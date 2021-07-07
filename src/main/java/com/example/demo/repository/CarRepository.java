package com.example.demo.repository;

import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<List<Car>> findAllCarsByType(CarType carType);
    Optional<List<Car>> findAllCarsByBrandIgnoreCaseContaining(String brand);
    Optional<List<Car>> findAllCarsByModelIgnoreCaseContaining(String model);
    Optional<List<Car>> findAllCarsByYearManuf(Integer yearManuf);
    Optional<List<Car>> findAllCarsByYearModel(Integer yearModel);
    Optional<List<Car>> findAllCarsByPrice(Double price);
    Optional<List<Car>> findAllCarsByQuantity(Integer quantity);
}
