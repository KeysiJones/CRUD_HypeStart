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
import reactor.core.publisher.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarroControllerTest {

    private Integer ano = 2021;
    private Double preco = 79990.90;
    private String marca = "Ferrari";
    private String modelo = "Spider";
    private Integer qtd = 1;

    @Mock
    CarroService service;

    @InjectMocks
    private CarroController controller;

    @Test
    public void listAllCarsShouldReturnSuccessfullResponse() {
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
    public void findCarrById() {
        Carro carroStub = CarroStub.create(TipoCarro.SPORT);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findCarById(1L)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorId(1L);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByTipoShouldReturnSuccessfullResponse() {
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
    public void findCarByTipoShouldReturnNotFound() {
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
    public void findCarByMarcaShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.carroBasico();
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByMarca(marca)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorMarca(marca);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByMarcaShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByMarca(marca)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorMarca(marca);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModeloShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.carroBasico();
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByModelo(modelo)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorModelo(modelo);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByModeloShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByModelo(modelo)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorModelo(modelo);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByAnoFabricacaoShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.create(TipoCarro.SPORT);
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByAnoFabricacao(ano)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorAnoFabricacao(ano);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByAnoFabricacaoShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByAnoFabricacao(ano)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorAnoFabricacao(ano);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByAnoModeloShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.create(TipoCarro.SPORT);
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByAnoModelo(ano)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorAnoModelo(ano);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByAnoModeloShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByAnoModelo(ano)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorAnoModelo(ano);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByPrecoShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.create(TipoCarro.SPORT);
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByPreco(preco)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorPreco(preco);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByPrecoShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByPreco(preco)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorPreco(preco);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByQuantidadeShouldReturnSuccessfullResponse() {
        Carro carro = CarroStub.create(TipoCarro.SPORT);
        List<Carro> carroStub = new ArrayList<>();
        carroStub.add(carro);
        ResponseEntity expectedResponse = new ResponseEntity<>(carroStub, HttpStatus.OK);
        when(service.findAllCarsByQuantidade(qtd)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorQuantidade(qtd);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findCarByQuantidadeShouldReturnNotFound() {
        ResponseEntity expectedResponse = CarroStub.carroNotFound();
        when(service.findAllCarsByQuantidade(qtd)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorQuantidade(qtd);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void createCarShouldReturnSuccessfullResponse() {
        Carro carroStub = CarroStub.create(TipoCarro.SEDAN);
        Mono<Carro> expectedResponse = Mono.just(carroStub);
        when(service.saveCar(carroStub)).thenReturn(expectedResponse);
        Mono<Carro> response = controller.cadastrar(carroStub);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.block().getTipo(), response.block().getTipo()),
                () -> assertEquals(expectedResponse.block().getId(), response.block().getId()),
                () -> assertEquals(expectedResponse.block().getModelo(), response.block().getModelo()),
                () -> assertEquals(expectedResponse.block().getMarca(), response.block().getMarca()),
                () -> assertEquals(expectedResponse.block().getAnoFabricacao(), response.block().getAnoFabricacao()),
                () -> assertEquals(expectedResponse.block().getAnoModelo(), response.block().getAnoModelo()),
                () -> assertEquals(expectedResponse.block().getPreco(), response.block().getPreco()),
                () -> assertEquals(expectedResponse.block().getQuantidade(), response.block().getQuantidade())
        );
    }

    @Test
    public void updateCarShouldReturnSuccessfullResponse() {
        Carro updateCar = new Carro("Fiat", "Mobi", TipoCarro.HATCH, 2020, 2021, 49990.90, 5);
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.updateCar(1L, updateCar)).thenReturn(expectedResponse);
        ResponseEntity response = controller.atualizarCadastro(1L, updateCar);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void deleteCarShouldReturnSuccessfullResponse() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.deleteCarById(1L)).thenReturn(expectedResponse);
        ResponseEntity response = controller.deletar(1L);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}