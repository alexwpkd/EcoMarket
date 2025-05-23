package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por cliente
    List<Pedido> findByCliente(Cliente cliente);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(String estado);
}
