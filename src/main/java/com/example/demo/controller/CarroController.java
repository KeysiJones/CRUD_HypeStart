package com.example.demo.controller;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.demo.util.Constants.PAGINA_INICIAL;

@RestController
@RequestMapping("/")
public class CarroController{

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public Mono<String> homePage() {
        return Mono.just(PAGINA_INICIAL);
    }

    @GetMapping("/api/carros")
    public Flux<List<Carro>> listaDeCarros() {
        List<Carro> carros = List.of( //Arrays.asList com return Flux.fromIterable
                new Carro(1L, "Mercedes","W12", TipoCarro.CORRIDA),
                new Carro(2L, "Toyota", "Yaris", TipoCarro.SEDAN),
                new Carro(3L, "Ford","EcoSport", TipoCarro.SPORT));
        return Flux.just(carros);
        //return clienteService.listAllCars();

    }

    @GetMapping("/api/carros/{id}")
    public Mono<Carro> buscarCarroPorId(@PathVariable Long id) {
        Carro carro = new Carro(1L, "Mercedes","W12", TipoCarro.CORRIDA);
        return Mono.just(carro);
        //return carroService.buscarCarroPorId();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/cadastrar")
    public Mono<Carro> cadastrar(@RequestBody Carro carro) {
        carro = new Carro(1L, "Mercedes","W12", TipoCarro.CORRIDA);
        return Mono.just(carro);
        //return  Mono.just(carroService.saveCar(carro));
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/api/atualizar/{id}")
    public Mono<Carro> atualizarCadastro(@PathVariable Long id, @RequestBody Carro carroASerAtualizado) {
        carroASerAtualizado = new Carro(1L, "Mercedes", "AMG", TipoCarro.CORRIDA);
        return Mono.just(carroASerAtualizado);
        //carroASerAtualizado = carroService.atualizarCarroPorId(id);
        //return Mono.just(carroService.updateCar(carroASerAtualizado));
    }

//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    @DeleteMapping("/api/delete/{id}")
//    public Mono<Carro> deletar(@PathVariable Long id) {
//        Carro carro = carro.getId();
//        return Mono.just(carroService.deleteCardById(id);
//    }
}
