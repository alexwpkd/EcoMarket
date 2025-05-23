package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedRepository;

    // Crear un nuevo pedido
    public Pedido crearPedido(Pedido pedido) {
        return pedRepository.save(pedido);
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        return pedRepository.findAll();
    }

    // Buscar pedido por ID
    public Optional<Pedido> buscarPorId(Long id) {
        return pedRepository.findById(id);
    }

    // Buscar pedidos por cliente
    public List<Pedido> buscarPorCliente(Cliente cliente) {
        return pedRepository.findByCliente(cliente);
    }

    // Buscar pedidos por estado
    public List<Pedido> buscarPorEstado(String estado) {
        return pedRepository.findByEstado(estado);
    }

    // Actualizar estado de un pedido
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstado(nuevoEstado);
            return pedRepository.save(pedido);
        }
        return null;
    }
}