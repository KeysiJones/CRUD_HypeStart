package com.example.demo.controller;
import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import com.example.demo.stub.CarroStub;
import com.example.demo.util.MyCustomHttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
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
    public void buscarCarroPorTipoShouldReturnMyCustomResponse() {
        ResponseEntity expectedResponse = new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o Tipo" + TipoCarro.SPORT + "foi encontrado"), HttpStatus.NOT_FOUND);
        when(service.findAllCarsByTipo(TipoCarro.SPORT)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarCarroPorTipo(TipoCarro.SPORT);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}