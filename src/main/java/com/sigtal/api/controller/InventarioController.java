package com.sigtal.api.controller;

import com.sigtal.api.model.Inventario;
import com.sigtal.api.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // =====================================================
    // LISTAR PRODUCTOS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<Inventario>> listarProductos() {

        return ResponseEntity.ok(
                inventarioService.listarProductos());
    }

    // =====================================================
    // CONSULTAR PRODUCTO POR ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerProducto(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                inventarioService.obtenerProducto(id));
    }

    // =====================================================
    // REGISTRAR PRODUCTO
    // =====================================================

    @PostMapping
    public ResponseEntity<Inventario> registrarProducto(
            @RequestBody Inventario producto) {

        Inventario productoGuardado =
                inventarioService.registrarProducto(producto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoGuardado);
    }

    // =====================================================
    // ACTUALIZAR PRODUCTO
    // =====================================================

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarProducto(
            @PathVariable Integer id,
            @RequestBody Inventario datos) {

        return ResponseEntity.ok(
                inventarioService.actualizarProducto(id, datos));
    }

    // =====================================================
    // ELIMINAR PRODUCTO
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(
            @PathVariable Integer id) {

        inventarioService.eliminarProducto(id);

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put(
                "mensaje",
                "Producto eliminado correctamente");

        return ResponseEntity.ok(respuesta);
    }
}