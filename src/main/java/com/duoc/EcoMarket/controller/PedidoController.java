package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Gestión de pedidos de clientes")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Crear nuevo pedido")
    @PostMapping("/crear")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevo);
    }

    @Operation(summary = "Listar todos los pedidos")
    @GetMapping("/listar")
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @Operation(summary = "Obtener pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Buscar pedidos por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pedidoService.buscarPorEstado(estado));
    }

    @Operation(summary = "Buscar pedidos por ID de cliente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        return ResponseEntity.ok(pedidoService.buscarPorCliente(cliente));
    }

    @Operation(summary = "Actualizar estado de un pedido")
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Pedido actualizado = pedidoService.actualizarEstado(id, nuevoEstado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
