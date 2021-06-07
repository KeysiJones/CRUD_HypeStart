package com.example.demo.controller;
import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.service.CarroService;
import com.example.demo.util.MyCustomHttpResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import org.slf4j.Logger;
import static com.example.demo.util.Constants.PAGINA_INICIAL;

@RestController
@RequestMapping("/")
public class CarroController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarroController.class);
    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<String> homePage() {
        return Mono.just(PAGINA_INICIAL);
    }

    @GetMapping("/api/carros")
    public Flux<Carro> listaDeCarros() {
        LOGGER.info("Iniciando consulta no banco de dados...");
        return service.listAllCars()
                .doOnNext(cars -> LOGGER.info("Retornando todos os carros cadastrados..."));
    }

    @GetMapping("/api/carro/{id}")
    public ResponseEntity<?> buscarCarroPorId(@PathVariable(name = "id") Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.findCarById(id);
    }

    @GetMapping("/api/carros/search")
    public ResponseEntity<?> buscarCarroPorTipo(@RequestParam TipoCarro tipoCarro) {
        LOGGER.info("Retornando todos os carros cadastrados do tipo {}", tipoCarro);
        return service.findAllCarsByTipo(tipoCarro);
    }

    @PostMapping("/api/cadastrar")
    public Mono<Carro> cadastrar(@RequestBody Carro carro) {
        LOGGER.info("Analisando dados enviados...");
        return service.saveCar(carro)
                .doOnNext(car -> LOGGER.info("Salvando um carro da {}", carro.getMarca()));
    }

    @PutMapping("/api/atualizar/{id}")
    public ResponseEntity<MyCustomHttpResponse> atualizarCadastro(@PathVariable Long id, @Valid @RequestBody Carro carro) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.updateCar(id, carro);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<MyCustomHttpResponse> deletar(@PathVariable Long id) {
        LOGGER.info("Verificando se o carro com o ID {} existe", id);
        return service.deleteCarById(id);
    }
}
