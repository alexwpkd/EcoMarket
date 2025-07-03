package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado.");
        }
    }

    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @Operation(summary = "Buscar productos por categoría")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }
}
