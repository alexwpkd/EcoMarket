package com.duoc.EcoMarket.repository;
import com.duoc.EcoMarket.model.Pedido;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepositor {
    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido registrarPedido(Pedido p) {
        pedidos.add(p);
        return p;
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    public Pedido actualizarEstado(int idPedido, String nuevoEstado) {
        for (Pedido p : pedidos) {
            if (p.getIdPedido() == idPedido) {
                p.setEstado(nuevoEstado);
                return p;
            }
        }
        return null;
    }
}