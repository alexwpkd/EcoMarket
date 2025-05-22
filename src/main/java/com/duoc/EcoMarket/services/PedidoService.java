package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido registrar(String correo, List<Producto> productos) {
        Pedido p = new Pedido(pedidoRepository.listarPedidos().size() + 1, correo, productos, "Pendiente");
        return pedidoRepository.registrarPedido(p);
    }

    public List<Pedido> listar() {
        return pedidoRepository.listarPedidos();
    }

    public Pedido actualizarEstado(int idPedido, String estado) {
        return pedidoRepository.actualizarEstado(idPedido, estado);
    }
}