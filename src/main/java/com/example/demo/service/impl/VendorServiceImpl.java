package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository repository;

    @Override
    public Mono<Vendor> saveVendor(Vendor vendor) { return Mono.just(repository.save(vendor));}

    @Override
    public Flux<Vendor> listAllVendors() { return  Flux.fromIterable(repository.findAll());}

    @Override
    public ResponseEntity<MyCustomHttpResponse> updateVendor(Long id, Vendor vendedor) {
        return repository.findById(id).map(vendor -> {
            vendor.setCpf(Optional.ofNullable(vendedor.getCpf()).orElse(vendor.getCpf()));
            vendor.setName(Optional.ofNullable(vendedor.getName()).orElse(vendor.getName()));
            vendor.setEmail(Optional.ofNullable(vendedor.getEmail()).orElse(vendor.getCpf()));
            vendor.setRegistration(Optional.ofNullable(vendedor.getRegistration()).orElse(vendor.getRegistration()));
            repository.save(vendor);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "ID " + id + " successfully updated."), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No records with ID " + id + " were found."),HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> deleteVendorById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "ID " + id + " successfully deleted."), HttpStatus.OK);
        }
        return  new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No records with ID " + id + " were found."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findVendorById(Long id) {
        Optional<Vendor> vendedor = repository.findById(id);
        if (vendedor.isPresent()) {
            return new ResponseEntity<>(vendedor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No vendors were found with ID " + id), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByName(String name) {
        Optional<List<Vendor>> vendedores = repository.findAllVendorsByNameIgnoreCaseContaining(name);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No vendors were found with name " + name), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByCpf(String cpf) {
        Optional<List<Vendor>> vendedores = repository.findAllVendorsByCpfIgnoreCaseContaining(cpf);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No vendors were found with CPF " + cpf), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByRegistration(String registration) {
        Optional<List<Vendor>> vendedores = repository.findAllVendorsByRegistrationIgnoreCaseContaining(registration);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No vendors were found with registration " + registration), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByEmail(String email) {
        Optional<List<Vendor>> vendedores = repository.findAllVendorsByEmailIgnoreCaseContaining(email);
        if (!vendedores.isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "No vendors were found with e-mail " + email), HttpStatus.NOT_FOUND);
    }
}
