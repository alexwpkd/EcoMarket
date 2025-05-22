package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository proRepository;

    // Crear o actualizar producto
    public Producto guardarProducto(Producto producto) {
        return proRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Producto> listarProductos() {
        return proRepository.findAll();
    }

    // Buscar producto por id
    public Optional<Producto> buscarPorId(Long id) {
        return proRepository.findById(id);
    }

    // Buscar productos por categoría (ejemplo filtro)
    public List<Producto> buscarPorCategoria(String categoria) {
        return proRepository.findByCategoria(categoria);
    }

    // Buscar productos por nombre (para barra de búsqueda)
    public List<Producto> buscarPorNombre(String nombre) {
        return proRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
