package com.example.demo.controller;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import com.example.demo.stub.CarroStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarroControllerTest {

    @Mock
    CarroService service;

    @InjectMocks
    private CarroController controller;

    @Test
    public void listAllCarsShouldReturnSuccessfulResponse() {
        Carro carroStub = CarroStub.create(TipoCarro.SEDAN);
        Flux<Carro> expectedResponse = Flux.just(carroStub, carroStub);
        when(service.listAllCars()).thenReturn(expectedResponse);
        Flux<Carro> response = controller.listaDeCarros();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.blockFirst().getId(), response.blockFirst().getId()),
                () -> assertEquals(expectedResponse.blockFirst().getMarca(), response.blockFirst().getMarca()),
                () -> assertEquals(expectedResponse.blockFirst().getModelo(), response.blockFirst().getModelo()),
                () -> assertEquals(expectedResponse.blockFirst().getTipo(), response.blockFirst().getTipo())
        );
    }

    @Test
    public void buscaCarroPorId() {
        Carro carroStub = CarroStub.create(TipoCarro.SPORT);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findCarById(Long.valueOf("1"))).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorId(Long.valueOf("1"));
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorTipoShouldReturnSuccessfulResponse() {
        Carro carro = CarroStub.create(TipoCarro.SPORT);
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByTipo(TipoCarro.SPORT)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorTipo(TipoCarro.SPORT);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorTipoShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByTipo(TipoCarro.SPORT)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorTipo(TipoCarro.SPORT);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorMarcaShouldReturnSuccessfulResponse() {
        Carro carro = CarroStub.carroBasico();
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByMarca("FERRARI")).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorMarca("FERRARI");
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorMarcaShouldReturnNotFound() {
        //ResponseEntity expectedResponse = new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o Tipo" + TipoCarro.SPORT + "foi encontrado"), HttpStatus.NOT_FOUND);
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByMarca("FERRARI")).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorMarca("FERRARI");
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorModeloShouldReturnSuccessfulResponse() {
        Carro carro = CarroStub.carroBasico();
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByModelo("SPIDER")).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorModelo("SPIDER");
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void buscarCarroPorModeloShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByModelo("SPIDER")).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorModelo("SPIDER");
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void cadastrarCarroShouldReturnSuccessfulResponse() {
        Carro carroStub = CarroStub.create(TipoCarro.SEDAN);
        Mono<Carro> expectedResponse = Mono.just(carroStub);
        when(service.saveCar(carroStub)).thenReturn(expectedResponse);
        Mono<Carro> response = controller.cadastrar(carroStub);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.block().getTipo(), response.block().getTipo()),
                () -> assertEquals(expectedResponse.block().getId(), response.block().getId()),
                () -> assertEquals(expectedResponse.block().getModelo(), response.block().getModelo()),
                () -> assertEquals(expectedResponse.block().getMarca(), response.block().getMarca())
                );
    }

    @Test
    public void atualizarCarroShouldReturnSuccessfulResponse() {
        Carro updateCar = new Carro("Fiat", "Mobi", TipoCarro.HATCH, 49990.90, 10);
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.updateCar(1L, updateCar)).thenReturn(expectedResponse);
        ResponseEntity response = controller.atualizarCadastro(1L, updateCar);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void deletarCarroShouldReturnSuccessfulResponse() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.deleteCarById(1L)).thenReturn(expectedResponse);
        ResponseEntity response = controller.deletar(1L);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}