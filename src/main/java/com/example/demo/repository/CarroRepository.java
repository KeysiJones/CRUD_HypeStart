package com.example.demo.repository;

import com.example.demo.enums.TipoCarro;
import com.example.demo.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Optional<List<Carro>> findAllCarsByTipo(TipoCarro tipoCarro);
    Optional<List<Carro>> findAllCarsByMarcaIgnoreCaseContaining(String marca);
    Optional<List<Carro>> findAllCarsByModeloIgnoreCaseContaining(String modelo);
}
