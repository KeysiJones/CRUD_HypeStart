package com.example.demo.service;

import com.example.demo.model.Vendor;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {

    Mono<Vendor> saveVendor(Vendor vendor);

    Flux<Vendor> listAllVendors();

    ResponseEntity<MyCustomHttpResponse> updateVendor(Long Id, Vendor vendor);

    ResponseEntity<MyCustomHttpResponse> deleteVendorById(Long Id);

    ResponseEntity<?> findVendorById(Long Id);

    ResponseEntity<?> findAllVendorsByName(String name);

    ResponseEntity<?> findAllVendorsByCpf(String cpf);

    ResponseEntity<?> findAllVendorsByRegistration(String registration);

    ResponseEntity<?> findAllVendorsByEmail(String email);
}
