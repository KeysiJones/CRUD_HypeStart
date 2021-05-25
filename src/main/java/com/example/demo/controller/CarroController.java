package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.demo.util.Constants.PAGINA_INICIAL;

@RestController
@RequestMapping("/")
public class CarroController {

    @GetMapping
    public Mono<String> homePage() {
        return Mono.just(PAGINA_INICIAL);
    }

    @GetMapping("/api/carros")
    public Flux<String> listaDeCarros() {
        return Flux.just("Um Celtinha,", " Uma Range Rover,", " Uma Lamborghini e", " Uma mercedes Benz");
    }

    @GetMapping("/api/carro/{id}")
    public Mono<String> buscarCarroPorId(@PathVariable(name = "id") Long id) {
        return Mono.just("Encontramos o carro com o ID " + id);
    }

    @PostMapping("/api/cadastrar")
    public Mono<String> cadastrar(@RequestBody String modelo) {
        return Mono.just("Carro com o modelo " + modelo + " cadastrado com sucesso");
    }

    @PutMapping("/api/atualizar/{id}")
    public Mono<String> atualizarCadastro(@PathVariable String id) {
        return Mono.just("Carro com o ID " + id + " atualizado com sucesso");
    }

    @DeleteMapping("/api/delete/{id}")
    public Mono<String> deletar(@PathVariable String id) {
        return Mono.just("Carro com o ID " + id + " deletado com sucesso");
    }
}
