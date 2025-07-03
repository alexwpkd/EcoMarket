package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.services.CarroComprasService;
import com.duoc.EcoMarket.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito obtenido"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{correoCliente}")
    public CarroCompras obtenerCarrito(@PathVariable String correoCliente) {
        return carritoService.obtener(correoCliente);
    }

    @Operation(summary = "Agregar producto al carrito")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito vaciado")
    })
    @DeleteMapping("/{correoCliente}/vaciar")
    public String vaciarCarrito(@PathVariable String correoCliente) {
        carritoService.vaciar(correoCliente);
        return "Carrito vaciado";
    }

    @Operation(summary = "Calcular total del carrito")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Total calculado")
    })
    @GetMapping("/{correoCliente}/total")
    public double total(@PathVariable String correoCliente) {
        return carritoService.total(correoCliente);
    }
}
