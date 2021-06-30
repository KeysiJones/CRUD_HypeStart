package com.example.demo.service;

import com.example.demo.model.Vendedor;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.*;

public interface VendedorService {

    Mono<Vendedor> saveVendor(Vendedor vendedor);

    Flux<Vendedor> listAllVendors();

    ResponseEntity<MyCustomHttpResponse> updateVendor(Long Id, Vendedor vendedor);

    ResponseEntity<MyCustomHttpResponse> deleteVendorById(Long Id);

    ResponseEntity<?> findVendorById(Long Id);

    ResponseEntity<?> findAllVendorsByNome(String nome);

    ResponseEntity<?> findAllVendorsByCpf(String cpf);

    ResponseEntity<?> findAllVendorsByMatricula(String matricula);

    ResponseEntity<?> findAllVendorsByEmail(String email);
}
