package com.sigtal.api.service;

import com.sigtal.api.dto.ClienteVehiculoRequest;
import com.sigtal.api.dto.VehiculoResponse;
import com.sigtal.api.model.Cliente;
import com.sigtal.api.model.Vehiculo;
import com.sigtal.api.repository.ClienteRepository;
import com.sigtal.api.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteVehiculoService {

    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;

    public ClienteVehiculoService(
            ClienteRepository clienteRepository,
            VehiculoRepository vehiculoRepository) {

        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    // =====================================================
    // LISTAR CLIENTES
    // =====================================================

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // =====================================================
    // CONSULTAR CLIENTE POR ID
    // =====================================================

    public Cliente obtenerCliente(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cliente no encontrado con ID: " + id));
    }

    // =====================================================
    // LISTAR VEHÍCULOS DE UN CLIENTE
    // =====================================================

    public List<VehiculoResponse> listarVehiculosPorCliente(
            Integer idCliente) {

        if (!clienteRepository.existsById(idCliente)) {
            throw new RuntimeException(
                    "Cliente no encontrado con ID: " + idCliente);
        }

        List<Vehiculo> vehiculos =
                vehiculoRepository.findByClienteIdCliente(idCliente);

        return vehiculos.stream()
                .map(VehiculoResponse::new)
                .toList();
    }

    // =====================================================
    // REGISTRAR CLIENTE + VEHÍCULO
    // =====================================================

    @Transactional
    public Map<String, Object> registrarClienteVehiculo(
            ClienteVehiculoRequest request) {

        // Validar documento
        if (clienteRepository.findByDocumento(
                request.getDocumento()).isPresent()) {

            throw new RuntimeException(
                    "Ya existe un cliente con el documento: "
                    + request.getDocumento());
        }

        // Validar placa
        if (vehiculoRepository.findByPlaca(
                request.getPlaca()).isPresent()) {

            throw new RuntimeException(
                    "Ya existe un vehículo con la placa: "
                    + request.getPlaca());
        }

        // Crear cliente
        Cliente cliente = new Cliente();

        cliente.setNombre(request.getNombre());
        cliente.setDocumento(request.getDocumento());
        cliente.setTelefono(request.getTelefono());
        cliente.setCorreo(request.getCorreo());
        cliente.setDireccion(request.getDireccion());

        Cliente clienteGuardado =
                clienteRepository.save(cliente);

        // Crear vehículo
        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setPlaca(request.getPlaca());
        vehiculo.setMarca(request.getMarca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setAnio(request.getAnio());
        vehiculo.setColor(request.getColor());
        vehiculo.setTipoVehiculo(request.getTipoVehiculo());
        vehiculo.setCliente(clienteGuardado);

        Vehiculo vehiculoGuardado =
                vehiculoRepository.save(vehiculo);

        // Respuesta
        Map<String, Object> respuesta =
                new HashMap<>();

        respuesta.put(
                "mensaje",
                "Cliente y vehículo registrados correctamente");

        respuesta.put("cliente", clienteGuardado);

        respuesta.put(
                "vehiculo",
                new VehiculoResponse(vehiculoGuardado));

        return respuesta;
    }

    // =====================================================
    // ACTUALIZAR CLIENTE
    // =====================================================

    public Cliente actualizarCliente(
            Integer id,
            ClienteVehiculoRequest request) {

        Cliente cliente = obtenerCliente(id);

        cliente.setNombre(request.getNombre());
        cliente.setDocumento(request.getDocumento());
        cliente.setTelefono(request.getTelefono());
        cliente.setCorreo(request.getCorreo());
        cliente.setDireccion(request.getDireccion());

        return clienteRepository.save(cliente);
    }

    // =====================================================
    // ACTUALIZAR VEHÍCULO
    // =====================================================

    public Vehiculo actualizarVehiculo(
            Integer idVehiculo,
            ClienteVehiculoRequest request) {

        Vehiculo vehiculo =
                vehiculoRepository.findById(idVehiculo)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Vehículo no encontrado con ID: "
                                        + idVehiculo));

        vehiculo.setPlaca(request.getPlaca());
        vehiculo.setMarca(request.getMarca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setAnio(request.getAnio());
        vehiculo.setColor(request.getColor());
        vehiculo.setTipoVehiculo(request.getTipoVehiculo());

        return vehiculoRepository.save(vehiculo);
    }

    // =====================================================
    // ELIMINAR CLIENTE
    // =====================================================

    @Transactional
    public void eliminarCliente(Integer id) {

        Cliente cliente = obtenerCliente(id);

        List<Vehiculo> vehiculos =
                vehiculoRepository.findByClienteIdCliente(id);

        vehiculoRepository.deleteAll(vehiculos);

        clienteRepository.delete(cliente);
    }

    // =====================================================
    // ELIMINAR VEHÍCULO
    // =====================================================

    public void eliminarVehiculo(Integer idVehiculo) {

        if (!vehiculoRepository.existsById(idVehiculo)) {
            throw new RuntimeException(
                    "Vehículo no encontrado con ID: "
                    + idVehiculo);
        }

        vehiculoRepository.deleteById(idVehiculo);
    }
}