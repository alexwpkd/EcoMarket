package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.services.CarroComprasService;
import com.duoc.EcoMarket.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@Tag(name = "Carro de Compras", description = "Operaciones sobre el carro de compras de los clientes")
public class CarroComprasController {

    @Autowired
    private CarroComprasService carritoService;

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener el carrito por correo del cliente")
    @GetMapping("/{correoCliente}")
    public CarroCompras obtenerCarrito(@PathVariable String correoCliente) {
        return carritoService.obtener(correoCliente);
    }

    @Operation(summary = "Agregar producto al carrito")
    @PostMapping("/{correoCliente}/agregar/{idProducto}")
    public String agregarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) {
            return "Producto no encontrado";
        }
        carritoService.agregarProducto(correoCliente, producto);
        return "Producto agregado al carrito";
    }

    @Operation(summary = "Eliminar producto del carrito")
    @DeleteMapping("/{correoCliente}/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) {
            return "Producto no encontrado";
        }
        carritoService.eliminarProducto(correoCliente, producto);
        return "Producto eliminado del carrito";
    }

    @Operation(summary = "Vaciar el carrito del cliente")
    @DeleteMapping("/{correoCliente}/vaciar")
    public String vaciarCarrito(@PathVariable String correoCliente) {
        carritoService.vaciar(correoCliente);
        return "Carrito vaciado";
    }

    @Operation(summary = "Calcular total del carrito")
    @GetMapping("/{correoCliente}/total")
    public double total(@PathVariable String correoCliente) {
        return carritoService.total(correoCliente);
    }
}
