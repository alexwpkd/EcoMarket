package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.repository.CarroComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroComprasService {
    @Autowired
    private CarroComprasRepository carroRepository;

    public CarroCompras obtener(String correoCliente) {
        return carroRepository.obtenerCarrito(correoCliente);
    }

    public void agregarProducto(String correoCliente, Producto producto) {
        CarroCompras carrito = carroRepository.obtenerCarrito(correoCliente);
        carrito.agregarProducto(producto);
        carroRepository.actualizarCarrito(carrito);
    }

    public void eliminarProducto(String correoCliente, Producto producto) {
        CarroCompras carrito = carroRepository.obtenerCarrito(correoCliente);
        carrito.eliminarProducto(producto);
        carroRepository.actualizarCarrito(carrito);
    }

    public void vaciar(String correoCliente) {
        CarroCompras carrito = carroRepository.obtenerCarrito(correoCliente);
        carrito.vaciarCarrito();
        carroRepository.actualizarCarrito(carrito);
    }

    public double total(String correoCliente) {
        return carroRepository.obtenerCarrito(correoCliente).calcularTotal();
    }
}