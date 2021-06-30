package com.example.demo.controller;

import com.example.demo.model.Vendedor;
import com.example.demo.service.VendedorService;
import com.example.demo.util.MyCustomHttpResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import org.slf4j.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class VendedorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendedorController.class);
    private final VendedorService service;

    public VendedorController(VendedorService service) { this.service = service; }

    @GetMapping("/api/list/vendors")
    public Flux<Vendedor> listaDeVendedores() {
        LOGGER.info("Iniciando consulta no banco de dados...");
        return service.listAllVendors()
                .doOnNext(vendors -> LOGGER.info("Retornando todos os vendedores cadastrados..."));
    }

    @GetMapping("/api/search/vendor/{id}")
    public ResponseEntity<?> buscarVendedorPorId(@PathVariable(name = "id") Long id) {
        LOGGER.info("Verificando se o vendedor com o ID {} existe", id);
        return service.findVendorById(id);
    }

    @GetMapping("api/search/vendors/by_cpf")
    public ResponseEntity<?> buscarVendedorPorCpf(@RequestParam String cpf) {
        LOGGER.info("Retornando todos os vendedores cadastrados com o CPF {} ...", cpf);
        return service.findAllVendorsByCpf(cpf);
    }

    @GetMapping("api/search/vendors/by_nome")
    public ResponseEntity<?> buscarVendedorPorNome(@RequestParam String nome) {
        LOGGER.info("Retornando todos os vendedores cadastrados com o nome {} ...", nome);
        return service.findAllVendorsByNome(nome);
    }

    @GetMapping("api/search/vendors/by_matricula")
    public ResponseEntity<?> buscarVendedorPorMatricula(@RequestParam String matricula) {
        LOGGER.info("Retornando todos os vendedores cadastrados com a matrícula {} ...", matricula);
        return service.findAllVendorsByMatricula(matricula);
    }

    @GetMapping("api/search/vendors/by_email")
    public ResponseEntity<?> buscarVendedorPorEmail(@RequestParam String email) {
        LOGGER.info("Retornando todos os vendedores cadastrados com o e-mail {} ...", email);
        return service.findAllVendorsByEmail(email);
    }

    @PostMapping("api/create/vendor")
    public Mono<Vendedor> cadastrar(@RequestBody Vendedor vendedor) {
        LOGGER.info("Analisando os dados enviados...");
        return service.saveVendor(vendedor)
                .doOnNext(vendor -> LOGGER.info("Salvando o vendedor {} ", vendedor.getNome()));
    }

    @PutMapping("/api/update/vendor/{id}")
    public ResponseEntity<MyCustomHttpResponse> atualizarCadastro(@PathVariable Long id, @Valid @RequestBody Vendedor vendedor) {
        LOGGER.info("Verificando se o vendedor com o ID {} existe", id);
        return service.updateVendor(id, vendedor);
    }

    @DeleteMapping("/api/delete/vendor/{id}")
    public ResponseEntity<MyCustomHttpResponse> deletar(@PathVariable Long id) {
        LOGGER.info("Verificando se o vendedor com o ID {} existe.", id);
        return service.deleteVendorById(id);
    }
}
