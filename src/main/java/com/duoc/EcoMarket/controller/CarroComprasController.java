package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.services.CarroComprasService;
import com.duoc.EcoMarket.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarroComprasController {

    @Autowired
    private CarroComprasService carritoService;

    @Autowired
    private ProductoService productoService;

    // Obtener el carrito de un cliente
    @GetMapping("/{correoCliente}")
    public CarroCompras obtenerCarrito(@PathVariable String correoCliente) {
        return carritoService.obtener(correoCliente);
    }

    // Agregar un producto al carrito
    @PostMapping("/{correoCliente}/agregar/{idProducto}")
    public String agregarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) {
            return "Producto no encontrado";
        }
        carritoService.agregarProducto(correoCliente, producto);
        return "Producto agregado al carrito";
    }

    // Eliminar un producto del carrito
    @DeleteMapping("/{correoCliente}/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) {
            return "Producto no encontrado";
        }
        carritoService.eliminarProducto(correoCliente, producto);
        return "Producto eliminado del carrito";
    }

    // Vaciar el carrito
    @DeleteMapping("/{correoCliente}/vaciar")
    public String vaciarCarrito(@PathVariable String correoCliente) {
        carritoService.vaciar(correoCliente);
        return "Carrito vaciado";
    }

    // Obtener total del carrito
    @GetMapping("/{correoCliente}/total")
    public double total(@PathVariable String correoCliente) {
        return carritoService.total(correoCliente);
    }
}
