package com.example.demo.service.impl;

import com.example.demo.model.Vendedor;
import com.example.demo.repository.VendedorRepository;
import com.example.demo.service.VendedorService;
import com.example.demo.util.MyCustomHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.*;
import java.util.*;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    VendedorRepository repository;

    @Override
    public Mono<Vendedor> saveVendor(Vendedor vendedor) { return Mono.just(repository.save(vendedor));}

    @Override
    public Flux<Vendedor> listAllVendors() { return  Flux.fromIterable(repository.findAll());}

    @Override
    public ResponseEntity<MyCustomHttpResponse> updateVendor(Long id, Vendedor vendedor) {
        return repository.findById(id).map(vendor -> {
            vendor.setCpf(Optional.ofNullable(vendedor.getCpf()).orElse(vendor.getCpf()));
            vendor.setNome(Optional.ofNullable(vendedor.getNome()).orElse(vendor.getNome()));
            vendor.setEmail(Optional.ofNullable(vendedor.getEmail()).orElse(vendor.getCpf()));
            vendor.setMatricula(Optional.ofNullable(vendedor.getMatricula()).orElse(vendor.getMatricula()));
            repository.save(vendor);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com ID " + id + " atualizado com sucesso"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum registro com o ID " + id + " foi encontrado."),HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> deleteVendorById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com o ID " + id + " deletado com sucesso."), HttpStatus.OK);
        }
        return  new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Registro com o ID " + id + " não foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findVendorById(Long id) {
        Optional<Vendedor> vendedor = repository.findById(id);
        if (vendedor.isPresent()) {
            return new ResponseEntity<>(vendedor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum vendedor com o ID " + id + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByNome(String nome) {
        Optional<List<Vendedor>> vendedores = repository.findAllVendorsByNomeIgnoreCaseContaining(nome);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum vendedor com o nome " + nome + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByCpf(String cpf) {
        Optional<List<Vendedor>> vendedores = repository.findAllVendorsByCpfIgnoreCaseContaining(cpf);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum vendedor com o CPF " + cpf + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByMatricula(String matricula) {
        Optional<List<Vendedor>> vendedores = repository.findAllVendorsByMatriculaIgnoreCaseContaining(matricula);
        if (!vendedores.get().isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum vendedor com a matrícula " + matricula + " foi encontrado."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllVendorsByEmail(String email) {
        Optional<List<Vendedor>> vendedores = repository.findAllVendorsByEmailIgnoreCaseContaining(email);
        if (!vendedores.isEmpty()) {
            return new ResponseEntity<>(vendedores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum vendedor com o e-mail " + email + " foi encontrado."), HttpStatus.NOT_FOUND);
    }
}
