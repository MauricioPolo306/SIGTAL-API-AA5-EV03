package com.sigtal.api.repository;

import com.sigtal.api.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    Optional<Inventario> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}