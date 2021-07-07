package com.example.demo.controller;

import com.example.demo.enums.CarType;
import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import com.example.demo.stub.CarStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    private Long id = 1L;
    private Integer year = 2021;
    private Double price = 89990.90;
    private String brand = "Fiat";
    private String model = "Argo HGT";
    private Integer qtd = 1;

    @Mock
    CarService service;

    @InjectMocks
    private CarController controller;

    @Test
    public void listAllCarsShouldReturnSuccessfulResponse() {
        Car carStub = CarStub.create(CarType.SEDAN);
        Flux<Car> expectedResponse = Flux.just(carStub, carStub);
        when(service.listAllCars()).thenReturn(expectedResponse);
        Flux<Car> response = controller.listOfCArs();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.blockFirst().getId(), response.blockFirst().getId()),
                () -> assertEquals(expectedResponse.blockFirst().getBrand(), response.blockFirst().getBrand()),
                () -> assertEquals(expectedResponse.blockFirst().getModel(), response.blockFirst().getModel()),
                () -> assertEquals(expectedResponse.blockFirst().getType(), response.blockFirst().getType())
        );
    }

    @Test
    public void findCarrById() {
        Car carStub = CarStub.create(CarType.SPORT);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findCarById(id)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarById(id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByTypeShouldReturnSuccessfulResponse() {
        Car car = CarStub.create(CarType.SPORT);
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByType(CarType.SPORT)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByType(CarType.SPORT);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByTypeShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByType(CarType.SPORT)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByType(CarType.SPORT);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByBrandShouldReturnSuccessfulResponse() {
        Car car = CarStub.basicCar();
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByBrand(brand)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByBrand(brand);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByBrandShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByBrand(brand)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByBrand(brand);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModelShouldReturnSuccessfulResponse() {
        Car car = CarStub.basicCar();
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByModel(model)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByModel(model);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModelShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByModel(model)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByModel(model);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByManufacturingYearShouldReturnSuccessfulResponse() {
        Car car = CarStub.create(CarType.SPORT);
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByManufacturingYear(year)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByManufacturingYear(year);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByManufacturingYearShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByManufacturingYear(year)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByManufacturingYear(year);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModelYearShouldReturnSuccessfulResponse() {
        Car car = CarStub.create(CarType.SPORT);
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByModelYear(year)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByModelYear(year);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModelYearShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByModelYear(year)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByModelYear(year);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByPriceShouldReturnSuccessfulResponse() {
        Car car = CarStub.create(CarType.SPORT);
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByPrice(price)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByPrice(price);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByPriceShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByPrice(price)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByPrice(price);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByQuantityShouldReturnSuccessfulResponse() {
        Car car = CarStub.create(CarType.SPORT);
        List<Car> carStub = new ArrayList<>();
        carStub.add(car);
        ResponseEntity expectedResponse = new ResponseEntity<>(carStub, HttpStatus.OK);
        when(service.findAllCarsByQuantity(qtd)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByQuantity(qtd);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByQuantityShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarStub.carNotFound();
        when(service.findAllCarsByQuantity(qtd)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchCarByQuantity(qtd);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void createCarShouldReturnSuccessfulResponse() {
        Car carStub = CarStub.create(CarType.SEDAN);
        Mono<Car> expectedResponse = Mono.just(carStub);
        when(service.saveCar(carStub)).thenReturn(expectedResponse);
        Mono<Car> response = controller.createCar(carStub);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.block().getType(), response.block().getType()),
                () -> assertEquals(expectedResponse.block().getId(), response.block().getId()),
                () -> assertEquals(expectedResponse.block().getModel(), response.block().getModel()),
                () -> assertEquals(expectedResponse.block().getBrand(), response.block().getBrand()),
                () -> assertEquals(expectedResponse.block().getYearManuf(), response.block().getYearManuf()),
                () -> assertEquals(expectedResponse.block().getYearModel(), response.block().getYearModel()),
                () -> assertEquals(expectedResponse.block().getPrice(), response.block().getPrice()),
                () -> assertEquals(expectedResponse.block().getQuantity(), response.block().getQuantity())
        );
    }

    @Test
    public void updateCarShouldReturnSuccessfulResponse() {
        Car updateCar = new Car(brand, model, CarType.HATCH, year, year, price, qtd);
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.updateCar(id, updateCar)).thenReturn(expectedResponse);
        ResponseEntity response = controller.updateCar(id, updateCar);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void deleteCarShouldReturnSuccessfulResponse() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.deleteCarById(id)).thenReturn(expectedResponse);
        ResponseEntity response = controller.deleteCar(id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}