package com.sigtal.api.service;

import com.sigtal.api.exception.RecursoNoEncontradoException;
import com.sigtal.api.model.Inventario;
import com.sigtal.api.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import com.sigtal.api.exception.SolicitudInvalidaException;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // =====================================================
    // LISTAR PRODUCTOS
    // =====================================================

    public List<Inventario> listarProductos() {
        return inventarioRepository.findAll();
    }

    // =====================================================
    // CONSULTAR PRODUCTO
    // =====================================================

    public Inventario obtenerProducto(Integer id) {

        return inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Producto no encontrado con ID: " + id));
    }

    // =====================================================
    // REGISTRAR PRODUCTO
    // =====================================================

    public Inventario registrarProducto(Inventario producto) {

        if (inventarioRepository.existsByCodigo(producto.getCodigo())) {
            throw new SolicitudInvalidaException(
        "Ya existe un producto con el código: "
        + producto.getCodigo());
        }

        return inventarioRepository.save(producto);
    }

    // =====================================================
    // ACTUALIZAR PRODUCTO
    // =====================================================

    public Inventario actualizarProducto(
            Integer id,
            Inventario datos) {

        Inventario producto = obtenerProducto(id);

        if (!producto.getCodigo().equals(datos.getCodigo())
                && inventarioRepository.existsByCodigo(datos.getCodigo())) {

            throw new SolicitudInvalidaException(
        "Ya existe un producto con el código: "
        + datos.getCodigo());
        }

        producto.setCodigo(datos.getCodigo());
        producto.setNombre(datos.getNombre());
        producto.setCategoria(datos.getCategoria());
        producto.setMarca(datos.getMarca());
        producto.setDescripcion(datos.getDescripcion());
        producto.setCantidad(datos.getCantidad());
        producto.setStockMinimo(datos.getStockMinimo());
        producto.setPrecioCompra(datos.getPrecioCompra());
        producto.setPrecioVenta(datos.getPrecioVenta());
        producto.setProveedor(datos.getProveedor());
        producto.setEstado(datos.getEstado());

        return inventarioRepository.save(producto);
    }

    // =====================================================
    // ELIMINAR PRODUCTO
    // =====================================================

    public void eliminarProducto(Integer id) {

        if (!inventarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Producto no encontrado con ID: " + id);
        }

        inventarioRepository.deleteById(id);
    }
}