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


    public Pedido crearPedido(Pedido pedido) {
        return pedRepository.save(pedido);
    }


    public List<Pedido> obtenerTodos() {
        return pedRepository.findAll();
    }


    public Optional<Pedido> buscarPorId(Long id) {
        return pedRepository.findById(id);
    }


    public List<Pedido> buscarPorCliente(Cliente cliente) {
        return pedRepository.findByCliente(cliente);
    }


    public List<Pedido> buscarPorEstado(String estado) {
        return pedRepository.findByEstado(estado);
    }


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