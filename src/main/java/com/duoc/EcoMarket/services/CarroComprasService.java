package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.CarroComprasRepository;
import com.duoc.EcoMarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroComprasService {

    @Autowired
    private CarroComprasRepository ccRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public CarroCompras obtener(String correoCliente) {
        Cliente cliente = clienteRepository.findByCorreo(correoCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return null;
        }

        CarroCompras carrito = ccRepository.findByCliente(cliente).orElse(null);
        if (carrito == null) {
            carrito = new CarroCompras();
            carrito.setCliente(cliente);
            ccRepository.save(carrito);
        }

        return carrito;
    }

    public void agregarProducto(String correoCliente, Producto producto) {
        CarroCompras carrito = obtener(correoCliente);
        if (carrito != null) {
            carrito.agregarProducto(producto);
            ccRepository.save(carrito);
        }
    }

    public void eliminarProducto(String correoCliente, Producto producto) {
        CarroCompras carrito = obtener(correoCliente);
        if (carrito != null) {
            carrito.eliminarProducto(producto);
            ccRepository.save(carrito);
        }
    }

    public void vaciar(String correoCliente) {
        CarroCompras carrito = obtener(correoCliente);
        if (carrito != null) {
            carrito.vaciarCarrito();
            ccRepository.save(carrito);
        }
    }

    public double total(String correoCliente) {
        CarroCompras carrito = obtener(correoCliente);
        if (carrito != null) {
            return carrito.calcularTotal();
        }
        return 0.0;
    }
}

