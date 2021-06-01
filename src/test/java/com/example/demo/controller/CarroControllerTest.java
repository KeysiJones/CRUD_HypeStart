package com.example.demo.controller;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

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

}
