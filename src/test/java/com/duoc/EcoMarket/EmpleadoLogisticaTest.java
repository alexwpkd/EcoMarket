package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.repository.EmpleadoLogisticaRepository;
import com.duoc.EcoMarket.repository.PedidoRepository;
import com.duoc.EcoMarket.services.LogisticaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoLogisticaTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmpleadoLogisticaRepository empleadoLogisticaRepository;

    @Autowired
    private LogisticaService logisticaService;

    @Test
    @DisplayName("Asignar pedido a empleado de logística")
    void testAsignarPedidoAEmpleado() {
        // Crear empleado logística
        EmpleadoLogistica empleado = empleadoLogisticaRepository.findByCorreo("empleado_logistica@correo.com");
        if (empleado == null) {
            empleado = new EmpleadoLogistica();
            empleado.setNombre("EmpleadoLog");
            empleado.setCorreo("empleado_logistica@correo.com");
            empleado.setContraseña("123");
            empleado.setTelefono(12345678);
            empleado = empleadoLogisticaRepository.save(empleado);
        }


        // Crear cliente
        Cliente cliente = clienteRepository.findByCorreo("cliente_asignacion@correo.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNombre("Cliente Pedido");
            cliente.setCorreo("cliente_asignacion@correo.com");
            cliente.setDireccion("Dirección");
            cliente.setTelefono(99999999);
            cliente.setContraseña("pass");
            cliente = clienteRepository.save(cliente);
        }

        // Crear pedido
        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEmpleadoLogistica(empleado);
        pedido.setEstado("Preparando");
        pedido.setTotal(20000);
        pedidoRepository.save(pedido);


        // Asignar pedido
        Pedido resultado = logisticaService.asignarPedido(pedido.getId(), empleado.getId());

        assertNotNull(resultado);
        assertEquals(empleado.getId(), resultado.getEmpleadoLogistica().getId());
    }

    @Test
    @DisplayName("Actualizar estado de pedido")
    void testActualizarEstadoPedido() {
        Cliente cliente = clienteRepository.findByCorreo("cliente_estado@example.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNombre("Cliente Estado");
            cliente.setCorreo("cliente_estado@example.com");
            cliente.setDireccion("Calle 2");
            cliente.setTelefono(11111111);
            cliente.setContraseña("pass");
            cliente = clienteRepository.save(cliente);
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado("Pendiente");
        pedido.setTotal(15000);
        pedido = pedidoRepository.save(pedido);

        Pedido actualizado = logisticaService.actualizarEstadoPedido(pedido.getId(), "Despachado");

        assertNotNull(actualizado);
        assertEquals("Despachado", actualizado.getEstado());
    }

    @Test
    @Transactional
    @DisplayName("Obtener pedidos asignados a empleado")
    void testObtenerPedidosAsignados() {
        // Buscar o crear empleado con correo único
        EmpleadoLogistica empleado = empleadoLogisticaRepository.findByCorreo("empleado_pedidos@correo.com");
        if (empleado == null) {
            empleado = new EmpleadoLogistica();
            empleado.setNombre("Empleado Pedidos");
            empleado.setCorreo("empleado_pedidos@correo.com");
            empleado.setContraseña("abc123");
            empleado.setTelefono(12345678);
            empleado = empleadoLogisticaRepository.save(empleado);
        }

        // Buscar o crear cliente con correo único
        Cliente cliente = clienteRepository.findByCorreo("cliente_pedidos@correo.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setNombre("Cliente Pedido");
            cliente.setCorreo("cliente_pedidos@correo.com");
            cliente.setDireccion("Dirección");
            cliente.setTelefono(88888888);
            cliente.setContraseña("clave");
            cliente = clienteRepository.save(cliente);
        }

        // Crear pedido asignado al empleado
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEmpleadoLogistica(empleado);
        pedido.setEstado("Preparando");
        pedido.setTotal(20000);
        pedido = pedidoRepository.save(pedido);

        // Obtener pedidos asignados desde el servicio
        List<Pedido> pedidos = logisticaService.obtenerPedidosAsignados(empleado.getId());

        // Validar
        assertNotNull(pedidos);
        Pedido finalPedido = pedido;
        assertTrue(pedidos.stream().anyMatch(p -> p.getId().equals(finalPedido.getId())));
    }


    @Test
    @DisplayName("Buscar empleado de logística por correo")
    void testBuscarEmpleadoPorCorreo() {
        String correo = "busqueda_empleado@correo.com";
        EmpleadoLogistica existente = empleadoLogisticaRepository.findByCorreo(correo);
        if (existente == null) {
            EmpleadoLogistica nuevo = new EmpleadoLogistica();
            nuevo.setNombre("Empleado Buscado");
            nuevo.setCorreo(correo);
            nuevo.setContraseña("123");
            nuevo.setTelefono(44444444);
            nuevo = empleadoLogisticaRepository.save(nuevo);
        }

        EmpleadoLogistica resultado = logisticaService.buscarPorCorreo(correo);
        assertNotNull(resultado);
        assertEquals(correo, resultado.getCorreo());
    }
}
