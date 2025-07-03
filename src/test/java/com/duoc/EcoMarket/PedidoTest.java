package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.repository.PedidoRepository;
import com.duoc.EcoMarket.services.PedidoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoService pedidoService;

    @Test
    @DisplayName("Crear pedido correctamente")
    void testCrearPedido() {
        Cliente cliente = clienteRepository.findByCorreo("pedido_cliente@correo.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNombre("Cliente Pedido");
            cliente.setCorreo("pedido_cliente@correo.com");
            cliente.setDireccion("Direccion X");
            cliente.setTelefono(99999999);
            cliente.setContraseña("clave123");
            cliente = clienteRepository.save(cliente);
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("Pendiente");
        pedido.setTotal(15000);

        Pedido guardado = pedidoService.crearPedido(pedido);

        assertNotNull(guardado);
        assertEquals("Pendiente", guardado.getEstado());
    }

    @Test
    @DisplayName("Buscar pedido por ID")
    void testBuscarPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        pedido.setTotal(10000);
        pedido = pedidoRepository.save(pedido);

        Optional<Pedido> encontrado = pedidoService.buscarPorId(pedido.getId());

        assertTrue(encontrado.isPresent());
        assertEquals(pedido.getId(), encontrado.get().getId());
    }

    @Test
    @DisplayName("Buscar pedidos por estado")
    void testBuscarPorEstado() {
        Pedido pedido = new Pedido();
        pedido.setEstado("Despachado");
        pedido.setTotal(12000);
        pedido = pedidoRepository.save(pedido);

        Long pedidoId = pedido.getId(); // ← Copia del ID

        List<Pedido> pedidos = pedidoService.buscarPorEstado("Despachado");

        assertFalse(pedidos.isEmpty());
        assertTrue(pedidos.stream().anyMatch(p -> p.getId().equals(pedidoId)));
    }


    @Test
    @DisplayName("Actualizar estado de pedido")
    void testActualizarEstado() {
        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        pedido.setTotal(8000);
        pedido = pedidoRepository.save(pedido);

        Pedido actualizado = pedidoService.actualizarEstado(pedido.getId(), "Entregado");

        assertNotNull(actualizado);
        assertEquals("Entregado", actualizado.getEstado());
    }
}
