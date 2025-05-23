package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Registrar venta a partir de un pedido y empleado de ventas
    @PostMapping("/registrar")
    public Venta registrarVenta(@RequestParam Long pedidoId, @RequestParam Long empleadoVentasId) {
        Venta venta = ventaService.registrarVenta(pedidoId, empleadoVentasId);
        if (venta == null) {
            throw new RuntimeException("No se pudo registrar la venta. Pedido o Empleado de ventas no encontrado.");
        }
        return venta;
    }

    // Consultar inventario (productos disponibles)
    @GetMapping("/inventario")
    public List<Producto> consultarInventario() {
        return ventaService.consultarInventario();
    }

    // Generar factura básica por venta
    @GetMapping("/{ventaId}/factura")
    public String generarFactura(@PathVariable Long ventaId) {
        return ventaService.generarFactura(ventaId);
    }
}
