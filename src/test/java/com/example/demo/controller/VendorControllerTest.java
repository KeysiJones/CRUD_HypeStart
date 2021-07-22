package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import com.example.demo.stub.VendorStub;
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
public class VendorControllerTest {

    private Long id = 1L;
    private String name = "Joao da Silva";
    private String cpf = "22483611060";
    private String registration = "99999";
    private String email = "vendor@hypeflame.com.br";

    @Mock
    VendorService service;

    @InjectMocks
    private VendorController controller;

    @Test
    public void listAllVendorsShouldReturnSuccessfulResponse() {
        Vendor vendorStub = VendorStub.create();
        Flux<Vendor> expectedResponse = Flux.just(vendorStub, vendorStub);
        when(service.listAllVendors()).thenReturn(expectedResponse);
        Flux<Vendor> response = controller.listOfVendors();
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.blockFirst().getId(), response.blockFirst().getId()),
                () -> assertEquals(expectedResponse.blockFirst().getName(), response.blockFirst().getName()),
                () -> assertEquals(expectedResponse.blockFirst().getRegistration(), response.blockFirst().getRegistration()),
                () -> assertEquals(expectedResponse.blockFirst().getCpf(), response.blockFirst().getCpf()),
                () -> assertEquals(expectedResponse.blockFirst().getEmail(), response.blockFirst().getEmail())
        );
    }

    @Test
    public void findVendorById() {
        Vendor vendorStub = VendorStub.create();
        ResponseEntity expectedResponse = new ResponseEntity<>(vendorStub, HttpStatus.OK);
        when(service.findVendorById(id)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorById(id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByNameShouldReturnFullResponse() {
        Vendor vendor = VendorStub.create();
        List<Vendor> vendorStub = new ArrayList<>();
        vendorStub.add(vendor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendorStub, HttpStatus.OK);
        when(service.findAllVendorsByName(name)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByName(name);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByNameShouldReturnNotFound() {
        ResponseEntity expectedResponse = VendorStub.vendorNotFound();
        when(service.findAllVendorsByName(name)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByName(name);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByCpfShouldReturnFullResponse() {
        Vendor vendor = VendorStub.create();
        List<Vendor> vendorStub = new ArrayList<>();
        vendorStub.add(vendor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendorStub, HttpStatus.OK);
        when(service.findAllVendorsByCpf(cpf)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByCpf(cpf);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByCpfShouldReturnNotFound() {
        ResponseEntity expectedResponse = VendorStub.vendorNotFound();
        when(service.findAllVendorsByCpf(cpf)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByCpf(cpf);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByRegistrationShouldReturnFullResponse() {
        Vendor vendor = VendorStub.create();
        List<Vendor> vendorStub = new ArrayList<>();
        vendorStub.add(vendor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendorStub, HttpStatus.OK);
        when(service.findAllVendorsByRegistration(registration)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByRegistration(registration);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByRegistrationShouldReturnNotFound() {
        ResponseEntity expectedResponse = VendorStub.vendorNotFound();
        when(service.findAllVendorsByRegistration(name)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByRegistration(name);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByEmailShouldReturnFullResponse() {
        Vendor vendor = VendorStub.create();
        List<Vendor> vendorStub = new ArrayList<>();
        vendorStub.add(vendor);
        ResponseEntity expectedResponse = new ResponseEntity<>(vendorStub, HttpStatus.OK);
        when(service.findAllVendorsByEmail(email)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByEmail(email);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getBody(), response.getBody()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void findVendorByEmailShouldReturnNotFound() {
        ResponseEntity expectedResponse = VendorStub.vendorNotFound();
        when(service.findAllVendorsByEmail(email)).thenReturn(expectedResponse);
        ResponseEntity response = controller.searchVendorByEmail(email);
        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(response.hasBody()),
                () -> assertEquals(expectedResponse.getStatusCodeValue(), response.getStatusCodeValue()),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void createVendorShouldReturnSuccessfulResponse() {
        Vendor vendorStub = VendorStub.create();
        Mono<Vendor> expectedResponse = Mono.just(vendorStub);
        when(service.saveVendor(vendorStub)).thenReturn(expectedResponse);
        Mono<Vendor> response = controller.createVendor(vendorStub);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.block().getName(), response.block().getName()),
                () -> assertEquals(expectedResponse.block().getCpf(), response.block().getCpf()),
                () -> assertEquals(expectedResponse.block().getRegistration(), response.block().getRegistration()),
                () -> assertEquals(expectedResponse.block().getEmail(), response.block().getEmail())
        );
    }

    @Test
    public void updateVendorShouldReturnSuccessfulResponse() {
        Vendor updateVendor = new Vendor(name, registration, cpf, email);
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.updateVendor(id, updateVendor)).thenReturn(expectedResponse);
        ResponseEntity response = controller.updateVendor(id, updateVendor);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }

    @Test
    public void deleteVendorShouldReturnSuccessfulResponse() {
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        when(service.deleteVendorById(id)).thenReturn(expectedResponse);
        ResponseEntity response = controller.deleteVendor(id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(expectedResponse.getStatusCode(), response.getStatusCode())
        );
    }
}
