package com.sigtal.api.repository;

import com.sigtal.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumento(String documento);

    Optional<Cliente> findByTelefono(String telefono);

    Optional<Cliente> findByCorreo(String correo);
}