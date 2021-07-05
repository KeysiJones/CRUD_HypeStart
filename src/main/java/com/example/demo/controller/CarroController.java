package com.example.demo.controller;

import com.example.demo.client.CarBrandResponse;
import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import com.example.demo.util.MyCustomHttpResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "List all Brands from Tabela Fipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned brands from Tabela Fipe", response = CarBrandResponse.class),
            @ApiResponse(code = 404, message = "No brands were found in  Tabela Fipe")
    })
    @GetMapping("/api/carros/brand")
    public Flux<CarBrandResponse> retrieveAllCarBrands() {
        LOGGER.info("Return brands from Tabela Fipe");
        return service.retrieveAllCarBrands();
    }

    @ApiOperation("Return the price from Tabela Fipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a price from Tabela Fipe")
    })
    @GetMapping("/api/carros/price/{brandCode}/{modelCode}/{yearId}")
    public Mono<String> retrieveCarWithPrice(
            @ApiParam(name = "brandCode", value = "Brand identifier")
            @PathVariable String brandCode,
            @ApiParam(name = "modelCode", value = "Model identifier")
            @PathVariable String modelCode,
            @ApiParam(name = "yearId", value = "YearId")
            @PathVariable String yearId) {
        LOGGER.info("return a car with price from Tabela Fipe");
        return service.retrieveCarWithPrice(brandCode, modelCode, yearId);
    }

    @ApiOperation("List all available cars in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request all available cars from database")
    })
    @GetMapping("/api/list/cars")
    public Flux<Carro> listaDeCarros() {
        LOGGER.info("Iniciando consulta no banco de dados...");
        return service.listAllCars()
                .doOnNext(cars -> LOGGER.info("Retornando todos os carros cadastrados..."));
    }

    @ApiOperation("Search a car by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by ID")
    })
    @GetMapping("/api/search/car/{id}")
    public ResponseEntity<?> buscarCarroPorId(@PathVariable(name = "id") Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.findCarById(id);
    }

    @ApiOperation("Search a car by type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Type")
    })
    @GetMapping("/api/search/cars/by_tipo")
    public ResponseEntity<?> buscarCarroPorTipo(@RequestParam TipoCarro tipoCarro) {
        LOGGER.info("Retornando todos os carros cadastrados do tipo {} ", tipoCarro);
        return service.findAllCarsByTipo(tipoCarro);
    }

    @ApiOperation("Search a car by brand")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Brand")
    })
    @GetMapping("/api/search/cars/by_marca")
    public ResponseEntity<?> buscarCarroPorMarca(@RequestParam String marca) {
        LOGGER.info("Retornando todos os carros cadastrados da marca {} ", marca);
        return service.findAllCarsByMarca(marca);
    }

    @ApiOperation("Search a car by model")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by Model")
    })
    @GetMapping("/api/search/cars/by_modelo")
    public ResponseEntity<?> buscarCarroPorModelo(@RequestParam String modelo) {
        LOGGER.info("Retornando todos os carros cadastrados do modelo {} .", modelo);
        return service.findAllCarsByModelo(modelo);
    }

    @ApiOperation("Search a car by manufacturing year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by manufacturing year")
    })
    @GetMapping("/api/search/cars/by_anoFabricacao")
    public ResponseEntity<?> buscarCarroPorAnoFabricacao(@RequestParam Integer anoFabricacao) {
        LOGGER.info("Retornando todos os carros fabricados em {} .", anoFabricacao);
        return service.findAllCarsByAnoFabricacao(anoFabricacao);
    }

    @ApiOperation("Search a car by model year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by model year")
    })
    @GetMapping("/api/search/cars/by_anoModelo")
    public ResponseEntity<?> buscarCarroPorAnoModelo(@RequestParam Integer anoModelo) {
        LOGGER.info("Retornando todos os carros ano/modelo {} .", anoModelo);
        return service.findAllCarsByAnoModelo(anoModelo);
    }

    @ApiOperation("Search a car by price")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by price")
    })
    @GetMapping("/api/search/cars/by_preco")
    public ResponseEntity<?> buscarCarroPorPreco(@RequestParam Double preco) {
        LOGGER.info("Retornando todos os carros cadastrados com o valor de {} Reais.", preco);
        return service.findAllCarsByPreco(preco);
    }

    @ApiOperation("Search a car by quantity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request a cars by quantity")
    })
    @GetMapping("/api/search/cars/by_qtd")
    public ResponseEntity<?> buscarCarroPorQuantidade(@RequestParam Integer quantidade) {
        LOGGER.info("Retornando todos os carros com {} itens em estoque.", quantidade);
        return service.findAllCarsByQuantidade(quantidade);
    }

    @ApiOperation("Create a new car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A new Car was created in database")
    })
    @PostMapping("/api/create/car")
    public Mono<Carro> cadastrar(@RequestBody Carro carro) {
        LOGGER.info("Analisando dados enviados...");
        return service.saveCar(carro)
                .doOnNext(car -> LOGGER.info("Salvando um carro da {} ", carro.getMarca()));
    }

    @ApiOperation("Update a car registration")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated car register")
    })
    @PutMapping("/api/update/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> atualizarCadastro(@PathVariable Long id, @Valid @RequestBody Carro carro) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.updateCar(id, carro);
    }

    @ApiOperation("Delete a car by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted car register")
    })
    @DeleteMapping("/api/delete/car/{id}")
    public ResponseEntity<MyCustomHttpResponse> deletar(@PathVariable Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.deleteCarById(id);
    }
}

