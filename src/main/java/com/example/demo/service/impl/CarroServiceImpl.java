package com.example.demo.service.impl;
import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import com.example.demo.repository.CarroRepository;
import com.example.demo.service.CarroService;
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
public class CarroServiceImpl implements CarroService {

    @Autowired
    CarroRepository repository;

    @Override
    public Mono<Carro> saveCar(Carro carro) {
        return Mono.just(repository.save(carro));
    }

    @Override
    public Flux<Carro> listAllCars() {
        return Flux.fromIterable(repository.findAll());
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> updateCar(Long id, Carro carro) {
        return repository.findById(id).map(car -> {
            car.setMarca(Optional.ofNullable(carro.getMarca()).orElse(car.getMarca()));
            car.setModelo(Optional.ofNullable(carro.getModelo()).orElse(car.getModelo()));
            car.setTipo(Optional.ofNullable(carro.getTipo()).orElse(car.getTipo()));
            repository.save(car);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com ID " + id + " atualizado com sucesso"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum registro com o ID " + id + " foi encontrado"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<MyCustomHttpResponse> deleteCarById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.OK.value(), "Registro com o ID " + id + " deletado com sucesso"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Registro com o ID " + id + " não foi encontrado"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findCarById(Long id) {
        Optional<Carro> carro = repository.findById(id);
        if (carro.isPresent()) {
            return new ResponseEntity<>(carro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o ID " + id + " foi encontrado"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findAllCarsByTipo(TipoCarro tipoCarro) {
        Optional<List<Carro>> carros = repository.findAllCarsByTipo(tipoCarro);
        if (!carros.get().isEmpty()) {
            return new ResponseEntity<>(carros.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MyCustomHttpResponse(HttpStatus.NOT_FOUND.value(), "Nenhum carro com o Tipo" + tipoCarro + "foi encontrado"), HttpStatus.NOT_FOUND);
    }
}