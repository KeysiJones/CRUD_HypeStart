package com.example.demo.controller;

import com.example.demo.model.Vendedor;
import com.example.demo.service.VendedorService;
import com.example.demo.stub.VendedorStub;
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
public class VendedorControllerTest {

    @Mock
    VendedorService service;

    @InjectMocks
    private VendedorController controller;

    @Test
    public void listAllVendorsShouldReturnSuccessFullResponse() {
        Vendedor vendedorStub = VendedorStub.create();
        Flux<Vendedor> expectedResponse = Flux.just(vendedorStub, vendedorStub);
        when(service.listAllVendors()).thenReturn(expectedResponse);
        Flux<Vendedor> response = controller.listaDeVendedores();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.blockFirst().getId(), response.blockFirst().getId()),
                () -> assertEquals(expectedResponse.blockFirst().getNome(), response.blockFirst().getNome()),
                () -> assertEquals(expectedResponse.blockFirst().getMatricula(), response.blockFirst().getMatricula()),
                () -> assertEquals(expectedResponse.blockFirst().getCpf(), response.blockFirst().getCpf()),
                () -> assertEquals(expectedResponse.blockFirst().getEmail(), response.blockFirst().getEmail())
        );
    }

    @Test
    public void findVendorById() {
        Vendedor vendedorStub = VendedorStub.create();
        ResponseEntity expectedResponse = new ResponseEntity<>(vendedorStub, HttpStatus.OK);
        when(service.findVendorById(Long.valueOf("1"))).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorId(Long.valueOf("1"));
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByNameShouldReturnFullResponse() {
        String nome = "João da Silva";
        Vendedor vendedor = VendedorStub.create();
        List<Vendedor> vendedorStub = new ArrayList<>();
        vendedorStub.add(vendedor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendedorStub, HttpStatus.OK);
        when(service.findAllVendorsByNome(nome)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorNome(nome);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByNameShouldReturnNotFound() {
        String nome = "João da Silva";
        ResponseEntity expectedResponse = VendedorStub.vendedorNotFound();
        when(service.findAllVendorsByNome(nome)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorNome(nome);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByCpfShouldReturnFullResponse() {
        String cpf = "22483611060";
        Vendedor vendedor = VendedorStub.create();
        List<Vendedor> vendedorStub = new ArrayList<>();
        vendedorStub.add(vendedor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendedorStub, HttpStatus.OK);
        when(service.findAllVendorsByCpf(cpf)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorCpf(cpf);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByCpfShouldReturnNotFound() {
        String cpf = "22483611060";
        ResponseEntity expectedResponse = VendedorStub.vendedorNotFound();
        when(service.findAllVendorsByCpf(cpf)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorCpf(cpf);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByMatriculaShouldReturnFullResponse() {
        String matricula = "99999";
        Vendedor vendedor = VendedorStub.create();
        List<Vendedor> vendedorStub = new ArrayList<>();
        vendedorStub.add(vendedor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendedorStub, HttpStatus.OK);
        when(service.findAllVendorsByMatricula(matricula)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorMatricula(matricula);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByMatriculaShouldReturnNotFound() {
        String matricula = "99999";
        ResponseEntity expectedResponse = VendedorStub.vendedorNotFound();
        when(service.findAllVendorsByMatricula(matricula)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorMatricula(matricula);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByEmailShouldReturnFullResponse() {
        String email = "vendedor@hypeflame.com.br";
        Vendedor vendedor = VendedorStub.create();
        List<Vendedor> vendedorStub = new ArrayList<>();
        vendedorStub.add(vendedor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendedorStub, HttpStatus.OK);
        when(service.findAllVendorsByEmail(email)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorEmail(email);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByEmailShouldReturnNotFound() {
        String email = "vendedor@hypeflame.com.br";
        ResponseEntity expectedResponse = VendedorStub.vendedorNotFound();
        when(service.findAllVendorsByEmail(email)).thenReturn(expectedResponse);
        ResponseEntity response = controller.buscarVendedorPorEmail(email);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void createVendorShouldReturnSuccessfullResponse() {
        Vendedor vendedorStub = VendedorStub.create();
        Mono<Vendedor> expectedResponse = Mono.just(vendedorStub);
        when(service.saveVendor(vendedorStub)).thenReturn(expectedResponse);
        Mono<Vendedor> response = controller.cadastrar(vendedorStub);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.block().getNome(), response.block().getNome()),
                () -> assertEquals(expectedResponse.block().getCpf(), response.block().getCpf()),
                () -> assertEquals(expectedResponse.block().getMatricula(), response.block().getMatricula()),
                () -> assertEquals(expectedResponse.block().getEmail(), response.block().getEmail())
        );
    }

    @Test
    public void updateVendorShouldReturnSuccessfullResponse() {
        Vendedor updateVendor = new Vendedor("Pedro Cabral", "11111", "22483611060", "vendedor@agibank.com.br");
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.updateVendor(1L, updateVendor)).thenReturn(expectedResponse);
        ResponseEntity response = controller.atualizarCadastro(1L, updateVendor);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void deleteVendorShouldReturnSuccessfullResponse() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.deleteVendorById(1L)).thenReturn(expectedResponse);
        ResponseEntity response = controller.deletar(1L);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}
