package com.example.demo.repository;

import com.example.demo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<List<Vendedor>> findAllVendorsByNomeIgnoreCaseContaining(String nome);
    Optional<List<Vendedor>> findAllVendorsByCpfIgnoreCaseContaining(String cpf);
    Optional<List<Vendedor>> findAllVendorsByMatriculaIgnoreCaseContaining(String matricula);
    Optional<List<Vendedor>> findAllVendorsByEmailIgnoreCaseContaining(String email);
}
