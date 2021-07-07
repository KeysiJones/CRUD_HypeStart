package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import com.example.demo.util.MyCustomHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class VendorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);
    private final VendorService service;

    public VendorController(VendorService service) { this.service = service; }

    @GetMapping("list/vendors")
    public Flux<Vendor> listOfVendors() {
        LOGGER.info("Starting query in database...");
        return service.listAllVendors()
                .doOnNext(vendors -> LOGGER.info("Returning all registered vendors..."));
    }

    @GetMapping("search/vendor/{id}")
    public ResponseEntity<?> searchVendorById(@PathVariable(name = "id") Long id) {
        LOGGER.info("Checking if the vendor with the ID {} exists", id);
        return service.findVendorById(id);
    }

    @GetMapping("search/vendors/cpf")
    public ResponseEntity<?> searchVendorByCpf(@RequestParam String cpf) {
        LOGGER.info("Returning all registered vendors with CPF {} ...", cpf);
        return service.findAllVendorsByCpf(cpf);
    }

    @GetMapping("search/vendors/name")
    public ResponseEntity<?> searchVendorByName(@RequestParam String nome) {
        LOGGER.info("Returning all registered vendors with name {} ...", nome);
        return service.findAllVendorsByName(nome);
    }

    @GetMapping("search/vendors/registration")
    public ResponseEntity<?> searchVendorByRegistration(@RequestParam String registration) {
        LOGGER.info("Returning all registered vendors with registration {} ...", registration);
        return service.findAllVendorsByRegistration(registration);
    }

    @GetMapping("search/vendors/email")
    public ResponseEntity<?> searchVendorByEmail(@RequestParam String email) {
        LOGGER.info("Returning all registered vendors with e-mail {} ...", email);
        return service.findAllVendorsByEmail(email);
    }

    @PostMapping("create/vendor")
    public Mono<Vendor> createVendor(@RequestBody Vendor vendor) {
        LOGGER.info("Analyzing data sent...");
        return service.saveVendor(vendor)
                .doOnNext(seller -> LOGGER.info("Saving the vendor {} ", vendor.getName()));
    }

    @PutMapping("update/vendor/{id}")
    public ResponseEntity<MyCustomHttpResponse> updateVendor(@PathVariable Long id, @Valid @RequestBody Vendor vendor) {
        LOGGER.info("Checking if the vendor with ID {} exists", id);
        return service.updateVendor(id, vendor);
    }

    @DeleteMapping("delete/vendor/{id}")
    public ResponseEntity<MyCustomHttpResponse> deleteVendor(@PathVariable Long id) {
        LOGGER.info("Checking if the vendor with ID {} exists", id);
        return service.deleteVendorById(id);
    }
}
