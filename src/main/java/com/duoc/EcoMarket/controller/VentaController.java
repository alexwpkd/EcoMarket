package com.duoc.EcoMarket.controller;


import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venta")
public class VentaController { @Autowired
private VentaService ventaService;

    @PostMapping("/registrar")
    public Venta registrarVenta(@RequestParam String correoCliente, @RequestBody List<Producto> productos) {
        return ventaService.registrarVenta(correoCliente, productos);
    }

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }
}
