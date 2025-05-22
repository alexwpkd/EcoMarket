package com.duoc.EcoMarket.services;


import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta registrarVenta(String correoCliente, List<Producto> productos) {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        int nuevoId = ventaRepository.listar().size() + 1;
        Venta venta = new Venta(nuevoId, correoCliente, productos, total);
        return ventaRepository.registrar(venta);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.listar();
    }
}
