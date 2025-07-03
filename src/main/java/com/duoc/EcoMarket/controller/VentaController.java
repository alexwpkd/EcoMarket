package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@Tag(name = "Ventas", description = "Procesos de venta, facturación e inventario")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Operation(summary = "Registrar una nueva venta")
    @PostMapping("/registrar")
    public Venta registrarVenta(@RequestParam Long pedidoId, @RequestParam Long empleadoVentasId) {
        Venta venta = ventaService.registrarVenta(pedidoId, empleadoVentasId);
        if (venta == null) {
            throw new RuntimeException("No se pudo registrar la venta. Pedido o Empleado de ventas no encontrado.");
        }
        return venta;
    }

    @Operation(summary = "Consultar el inventario disponible")
    @GetMapping("/inventario")
    public List<Producto> consultarInventario() {
        return ventaService.consultarInventario();
    }

    @Operation(summary = "Generar factura de una venta por ID")
    @GetMapping("/{ventaId}/factura")
    public String generarFactura(@PathVariable Long ventaId) {
        return ventaService.generarFactura(ventaId);
    }
}
