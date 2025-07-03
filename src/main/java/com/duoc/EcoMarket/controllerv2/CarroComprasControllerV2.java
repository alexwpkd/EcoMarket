package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.CarroComprasModelAssembler;
import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.services.CarroComprasService;
import com.duoc.EcoMarket.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/carrito")
public class CarroComprasControllerV2 {

    @Autowired
    private CarroComprasService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CarroComprasModelAssembler assembler;

    @GetMapping("/{correoCliente}")
    public EntityModel<CarroCompras> obtenerCarrito(@PathVariable String correoCliente) {
        CarroCompras carrito = carritoService.obtener(correoCliente);
        return assembler.toModel(carrito);
    }

    @DeleteMapping("/{correoCliente}/vaciar")
    public String vaciarCarrito(@PathVariable String correoCliente) {
        carritoService.vaciar(correoCliente);
        return "Carrito vaciado";
    }

    @GetMapping("/{correoCliente}/total")
    public double total(@PathVariable String correoCliente) {
        return carritoService.total(correoCliente);
    }

    @PostMapping("/{correoCliente}/agregar/{idProducto}")
    public String agregarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) return "Producto no encontrado";
        carritoService.agregarProducto(correoCliente, producto);
        return "Producto agregado al carrito";
    }

    @DeleteMapping("/{correoCliente}/eliminar/{idProducto}")
    public String eliminarProducto(@PathVariable String correoCliente, @PathVariable Long idProducto) {
        Producto producto = productoService.buscarPorId(idProducto).orElse(null);
        if (producto == null) return "Producto no encontrado";
        carritoService.eliminarProducto(correoCliente, producto);
        return "Producto eliminado del carrito";
    }
}
