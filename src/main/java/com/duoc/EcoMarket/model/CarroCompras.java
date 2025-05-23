package com.duoc.EcoMarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "carro_compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ElementCollection
    @CollectionTable(name = "carrito_productos", joinColumns = @JoinColumn(name = "carrito_id"))
    @MapKeyJoinColumn(name = "producto_id")
    @Column(name = "cantidad")
    private Map<Producto, Integer> productos = new HashMap<>();

    // Agregar producto
    public void agregarProducto(Producto producto) {
        if (productos.containsKey(producto)) {
            int cantidad = productos.get(producto);
            productos.put(producto, cantidad + 1);
        } else {
            productos.put(producto, 1);
        }
    }

    // Eliminar producto
    public void eliminarProducto(Producto producto) {
        if (productos.containsKey(producto)) {
            int cantidad = productos.get(producto);
            if (cantidad > 1) {
                productos.put(producto, cantidad - 1);
            } else {
                productos.remove(producto);
            }
        }
    }

    // Vaciar carrito
    public void vaciarCarrito() {
        productos.clear();
    }

    // Calcular total
    public double calcularTotal() {
        double total = 0.0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }
}
