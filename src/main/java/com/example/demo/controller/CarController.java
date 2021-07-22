package com.example.demo.controller;

import com.example.demo.client.CarBrandResponse;
import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import com.example.demo.util.MyCustomHttpResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class CarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @ApiOperation(value = "List all Brands from FIPE Table")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned brands from FIPE Table", response = CarBrandResponse.class),
            @ApiResponse(code = 404, message = "No brands were found in  FIPE Table")
    })
    @GetMapping("cars/brand")
    public Flux<CarBrandResponse> retrieveAllCarBrands() {
        LOGGER.info("Return brands from FIPE Table");
        return service.retrieveAllCarBrands();
    }

    @ApiOperation("Return the price from FIPE Table")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a price from FIPE Table")
    })
    @GetMapping("cars/price/{brandCode}/{modelCode}/{yearId}")
    public Mono<String> retrievePrice(
            @ApiParam(name = "brandCode", value = "Brand identifier")
            @PathVariable String brandCode,
            @ApiParam(name = "modelCode", value = "Model identifier")
            @PathVariable String modelCode,
            @ApiParam(name = "yearId", value = "YearId")
            @PathVariable String yearId) {
        LOGGER.info("return a car with price from FIPE Table");
        return service.retrievePrice(brandCode, modelCode, yearId);
    }

    @ApiOperation("List all available cars in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request all available cars from database")
    })
    @GetMapping("list/cars")
    public Flux<Car> listOfCArs() {
        LOGGER.info("Starting query in database...");
        return service.listAllCars()
                .doOnNext(cars -> LOGGER.info("Returning all registered cars..."));
    }

    @ApiOperation("Search a car by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by ID")
    })
    @GetMapping("search/car/{id}")
    public ResponseEntity<?> searchCarById(@PathVariable(name = "id") Long id) {
        LOGGER.info("Checking if the car with the ID {} exists", id);
        return service.findCarById(id);
    }

    @ApiOperation("Search a car by type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Type")
    })
    @GetMapping("search/cars/type")
    public ResponseEntity<?> searchCarByType(@RequestParam CarType carType) {
        LOGGER.info("Returning all registered cars of the type {} ", carType);
        return service.findAllCarsByType(carType);
    }

    @ApiOperation("Search a car by brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Brand")
    })
    @GetMapping("search/cars/brand")
    public ResponseEntity<?> searchCarByBrand(@RequestParam String brand) {
        LOGGER.info("Returning all registered cars of the brand {} ", brand);
        return service.findAllCarsByBrand(brand);
    }

    @ApiOperation("Search a car by model")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Model")
    })
    @GetMapping("search/cars/model")
    public ResponseEntity<?> searchCarByModel(@RequestParam String model) {
        LOGGER.info("Returning all registered cars of the model {} .", model);
        return service.findAllCarsByModel(model);
    }

    @ApiOperation("Search a car by manufacturing year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by manufacturing year")
    })
    @GetMapping("search/cars/manufacturing")
    public ResponseEntity<?> searchCarByManufacturingYear(@RequestParam Integer yearManuf) {
        LOGGER.info("Returning all cars manufactured in {} .", yearManuf);
        return service.findAllCarsByManufacturingYear(yearManuf);
    }

    @ApiOperation("Search a car by model year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by model year")
    })
    @GetMapping("search/cars/modelYear")
    public ResponseEntity<?> searchCarByModelYear(@RequestParam Integer yearModel) {
        LOGGER.info("Returning all cars with model year {} .", yearModel);
        return service.findAllCarsByModelYear(yearModel);
    }

    @ApiOperation("Search a car by price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by price")
    })
    @GetMapping("search/cars/price")
    public ResponseEntity<?> searchCarByPrice(@RequestParam Double price) {
        LOGGER.info("Returning all registered cars with the value of {} BRL.", price);
        return service.findAllCarsByPrice(price);
    }

    @ApiOperation("Search a car by quantity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by quantity")
    })
    @GetMapping("search/cars/qtd")
    public ResponseEntity<?> searchCarByQuantity(@RequestParam Integer quantity) {
        LOGGER.info("Returning all cars with {} items in stock.", quantity);
        return service.findAllCarsByQuantity(quantity);
    }

    @ApiOperation("Create a new car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new Car was created in database")
    })
    @PostMapping("create/car")
    public Mono<Car> createCar(@RequestBody Car car) {
        LOGGER.info("Analyzing data sent...");
        return service.saveCar(car)
                .doOnNext(vehicle -> LOGGER.info("Saving a car from the brand {} ", car.getBrand()));
    }

    @ApiOperation("Update a car registration")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated car register")
    })
    @PutMapping("update/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> updateCar(@PathVariable Long id, @Valid @RequestBody Car car) {
        LOGGER.info("Checking if the car with ID {} exists", id);
        return service.updateCar(id, car);
    }

    @ApiOperation("Delete a car by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted car register")
    })
    @DeleteMapping("delete/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> deleteCar(@PathVariable Long id) {
        LOGGER.info("Checking if the car with ID {} exists", id);
        return service.deleteCarById(id);
    }
}

