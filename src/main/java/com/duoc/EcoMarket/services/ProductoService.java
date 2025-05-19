package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProducto {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar() {
        return productoRepository.listar();
    }

    public Producto guardar(Producto producto) {
        return productoRepository.guardar(producto);
    }

    public Producto actualizar(Producto producto) {
        return productoRepository.actualizar(producto);
    }

    public String salida(int id, int cantidad) {
        return productoRepository.salidaProducto(id, cantidad);
    }
}