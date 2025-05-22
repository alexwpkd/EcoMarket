package com.duoc.EcoMarket.controller;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/registrar")
    public Pedido registrar(@RequestParam String correo, @RequestBody List<Producto> productos) {
        return pedidoService.registrar(correo, productos);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @PutMapping("/estado")
    public Pedido actualizarEstado(@RequestParam int idPedido, @RequestParam String estado) {
        return pedidoService.actualizarEstado(idPedido, estado);
    }
}
