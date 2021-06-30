package com.example.demo.controller;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import com.example.demo.util.MyCustomHttpResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import javax.validation.Valid;
import org.slf4j.Logger;

@RestController
@RequestMapping("/")
public class CarroController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroController.class);
    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping("/api/list/cars")
    public Flux<Carro> listaDeCarros() {
        LOGGER.info("Iniciando consulta no banco de dados...");
        return service.listAllCars()
                .doOnNext(cars -> LOGGER.info("Retornando todos os carros cadastrados..."));
    }

    @GetMapping("/api/search/car/{id}")
    public ResponseEntity<?> buscarCarroPorId(@PathVariable(name = "id") Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.findCarById(id);
    }

    @GetMapping("/api/search/cars/by_tipo")
    public ResponseEntity<?> buscarCarroPorTipo(@RequestParam TipoCarro tipoCarro) {
        LOGGER.info("Retornando todos os carros cadastrados do tipo {} ", tipoCarro);
        return service.findAllCarsByTipo(tipoCarro);
    }

    @GetMapping("/api/search/cars/by_marca")
    public ResponseEntity<?> buscarCarroPorMarca(@RequestParam String marca) {
        LOGGER.info("Retornando todos os carros cadastrados da marca {} ", marca);
        return service.findAllCarsByMarca(marca);
    }

    @GetMapping("/api/search/cars/by_modelo")
    public ResponseEntity<?> buscarCarroPorModelo(@RequestParam String modelo) {
        LOGGER.info("Retornando todos os carros cadastrados do modelo {} .", modelo);
        return service.findAllCarsByModelo(modelo);
    }

    @GetMapping("/api/search/cars/by_anoFabricacao")
    public ResponseEntity<?> buscarCarroPorAnoFabricacao(@RequestParam Integer anoFabricacao) {
        LOGGER.info("Retornando todos os carros fabricados em {} .", anoFabricacao);
        return service.findAllCarsByAnoFabricacao(anoFabricacao);
    }

    @GetMapping("/api/search/cars/by_anoModelo")
    public ResponseEntity<?> buscarCarroPorAnoModelo(@RequestParam Integer anoModelo) {
        LOGGER.info("Retornando todos os carros ano/modelo {} .", anoModelo);
        return service.findAllCarsByAnoModelo(anoModelo);
    }

    @GetMapping("/api/search/cars/by_preco")
    public ResponseEntity<?> buscarCarroPorPreco(@RequestParam Double preco) {
        LOGGER.info("Retornando todos os carros cadastrados com o valor de {} Reais.", preco);
        return service.findAllCarsByPreco(preco);
    }

    @GetMapping("/api/search/cars/by_qtd")
    public ResponseEntity<?> buscarCarroPorQuantidade(@RequestParam Integer quantidade) {
        LOGGER.info("Retornando todos os carros com {} itens em estoque.", quantidade);
        return service.findAllCarsByQuantidade(quantidade);
    }

    @PostMapping("/api/create/car")
    public Mono<Carro> cadastrar(@RequestBody Carro carro) {
        LOGGER.info("Analisando dados enviados...");
        return service.saveCar(carro)
                .doOnNext(car -> LOGGER.info("Salvando um carro da {} ", carro.getMarca()));
    }

    @PutMapping("/api/update/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> atualizarCadastro(@PathVariable Long id, @Valid @RequestBody Carro carro) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.updateCar(id, carro);
    }

    @DeleteMapping("/api/delete/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> deletar(@PathVariable Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.deleteCarById(id);
    }
}
