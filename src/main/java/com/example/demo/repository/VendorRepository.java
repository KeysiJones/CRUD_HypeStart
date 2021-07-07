package com.example.demo.repository;

import com.example.demo.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<List<Vendor>> findAllVendorsByNameIgnoreCaseContaining(String name);
    Optional<List<Vendor>> findAllVendorsByCpfIgnoreCaseContaining(String cpf);
    Optional<List<Vendor>> findAllVendorsByRegistrationIgnoreCaseContaining(String registration);
    Optional<List<Vendor>> findAllVendorsByEmailIgnoreCaseContaining(String email);
}
