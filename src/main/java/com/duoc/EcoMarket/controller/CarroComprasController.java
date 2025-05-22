package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.services.CarroComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/carrito")
public class CarroComprasController {

    @Autowired
    private CarroComprasService carritoService;

    @GetMapping("/{correo}")
    public CarroCompras obtener(@PathVariable String correo) {
        return carritoService.obtener(correo);
    }

    @PostMapping("/agregar")
    public void agregarProducto(@RequestParam String correo, @RequestBody Producto producto) {
        carritoService.agregarProducto(correo, producto);
    }

    @DeleteMapping("/eliminar")
    public void eliminarProducto(@RequestParam String correo, @RequestBody Producto producto) {
        carritoService.eliminarProducto(correo, producto);
    }

    @DeleteMapping("/vaciar/{correo}")
    public void vaciar(@PathVariable String correo) {
        carritoService.vaciar(correo);
    }

    @GetMapping("/total/{correo}")
    public double total(@PathVariable String correo) {
        return carritoService.total(correo);
    }
}