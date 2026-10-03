package com.sigtal.api.controller;

import com.sigtal.api.dto.ClienteVehiculoRequest;
import com.sigtal.api.dto.VehiculoResponse;
import com.sigtal.api.model.Cliente;
import com.sigtal.api.model.Vehiculo;
import com.sigtal.api.service.ClienteVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes-vehiculos")
@CrossOrigin(origins = "*")
public class ClienteVehiculoController {

    private final ClienteVehiculoService service;

    public ClienteVehiculoController(ClienteVehiculoService service) {
        this.service = service;
    }

    // =====================================================
    // GET - LISTAR CLIENTES
    // =====================================================

    @GetMapping
    public ResponseEntity<?> listarClientes() {

        try {
            List<Cliente> clientes = service.listarClientes();
            return ResponseEntity.ok(clientes);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // GET - CONSULTAR CLIENTE POR ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Integer id) {

        try {
            Cliente cliente = service.obtenerCliente(id);
            return ResponseEntity.ok(cliente);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // GET - VEHÍCULOS DE UN CLIENTE
    // =====================================================

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<?> listarVehiculos(@PathVariable Integer id) {

        try {
            List<VehiculoResponse> vehiculos =
                    service.listarVehiculosPorCliente(id);

            return ResponseEntity.ok(vehiculos);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // POST - REGISTRAR CLIENTE + VEHÍCULO
    // =====================================================

    @PostMapping
    public ResponseEntity<?> registrar(
            @RequestBody ClienteVehiculoRequest request) {

        try {
            Map<String, Object> respuesta =
                    service.registrarClienteVehiculo(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(respuesta);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // PUT - ACTUALIZAR CLIENTE
    // =====================================================

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> actualizarCliente(
            @PathVariable Integer id,
            @RequestBody ClienteVehiculoRequest request) {

        try {
            Cliente cliente =
                    service.actualizarCliente(id, request);

            return ResponseEntity.ok(cliente);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // PUT - ACTUALIZAR VEHÍCULO
    // =====================================================

    @PutMapping("/vehiculo/{id}")
    public ResponseEntity<?> actualizarVehiculo(
            @PathVariable Integer id,
            @RequestBody ClienteVehiculoRequest request) {

        try {
            Vehiculo vehiculo =
                    service.actualizarVehiculo(id, request);

            return ResponseEntity.ok(vehiculo);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // DELETE - ELIMINAR CLIENTE Y SUS VEHÍCULOS
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(
            @PathVariable Integer id) {

        try {
            service.eliminarCliente(id);

            Map<String, String> respuesta = new HashMap<>();

            respuesta.put(
                    "mensaje",
                    "Cliente y sus vehículos eliminados correctamente"
            );

            return ResponseEntity.ok(respuesta);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // DELETE - ELIMINAR VEHÍCULO
    // =====================================================

    @DeleteMapping("/vehiculo/{id}")
    public ResponseEntity<?> eliminarVehiculo(
            @PathVariable Integer id) {

        try {
            service.eliminarVehiculo(id);

            Map<String, String> respuesta = new HashMap<>();

            respuesta.put(
                    "mensaje",
                    "Vehículo eliminado correctamente"
            );

            return ResponseEntity.ok(respuesta);

        } catch (RuntimeException e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(crearError(e.getMessage()));
        }
    }

    // =====================================================
    // RESPUESTA DE ERROR
    // =====================================================

    private Map<String, String> crearError(String mensaje) {

        Map<String, String> error = new HashMap<>();

        error.put(
                "error",
                mensaje != null ? mensaje : "Error desconocido"
        );

        return error;
    }
}