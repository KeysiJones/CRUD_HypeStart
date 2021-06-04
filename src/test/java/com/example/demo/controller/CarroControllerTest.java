package com.example.demo.controller;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        Carro carroStub = new Carro("LAMBORGHINI", "ALC-9000", TipoCarro.CORRIDA);
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
        Carro carroStub = new Carro("FERRARI", "SPIDER", TipoCarro.SPORT);
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

}
