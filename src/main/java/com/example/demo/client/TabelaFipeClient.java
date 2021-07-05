package com.example.demo.client;

import com.example.demo.controller.CarroController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class TabelaFipeClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(CarroController.class);

        private final String baseUrl = "parallelum.com.br/fipe/api";
        WebClient webClient = WebClient.create(baseUrl);

        //https://parallelum.com.br/fipe/api/v1/carros/marcas
        public Flux<CarBrandResponse> retrieveAllCarBrands() {
                return webClient.get()
                        .uri("/v1/carros/marcas")
                        .retrieve()
                        .bodyToFlux(CarBrandResponse.class);
        }

        //https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3
        public Mono<String> retrievePrice(String brandCode, String modelCode, String yearId) {
                return webClient.get()
                        .uri("/v1/carros/marcas/{brandCode}/modelos/{modelCode}/anos/{yearId}", brandCode, modelCode, yearId)
                        .retrieve()
                        .bodyToMono(CarTabelaFipeResponse.class)
                        .map(CarTabelaFipeResponse::getPrice);
        }
}
