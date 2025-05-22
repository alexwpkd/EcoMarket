package com.duoc.EcoMarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroCompras {
    private String correoCliente;
    private List<Producto> productos = new ArrayList<>();

    public String agregarProducto(Producto producto) {
         productos.add(producto);
         return "Se agrego el producto";
    }

    public String eliminarProducto(Producto producto) {
        productos.remove(producto);
        return "Se elimino el producto";
    }

    public String vaciarCarrito() {
        productos.clear();
        return "Se vacio el carrito";
    }

    public Double calcularTotal() {
        double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }
}
