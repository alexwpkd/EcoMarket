package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.services.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta registrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido o Empleado no encontrado")
    })
    @PostMapping("/registrar")
    public Venta registrarVenta(@RequestParam Long pedidoId, @RequestParam Long empleadoVentasId) {
        Venta venta = ventaService.registrarVenta(pedidoId, empleadoVentasId);
        if (venta == null) {
            throw new RuntimeException("No se pudo registrar la venta. Pedido o Empleado de ventas no encontrado.");
        }
        return venta;
    }

    @Operation(summary = "Consultar el inventario disponible")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventario listado correctamente")
    })
    @GetMapping("/inventario")
    public List<Producto> consultarInventario() {
        return ventaService.consultarInventario();
    }

    @Operation(summary = "Generar factura de una venta por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Factura generada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{ventaId}/factura")
    public String generarFactura(@PathVariable Long ventaId) {
        return ventaService.generarFactura(ventaId);
    }
}
