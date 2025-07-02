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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoLogisticaTest {

    @Autowired
    EmpleadoLogisticaRepository logisticaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    @Autowired
    LogisticaService logisticaService;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Crear y verificar empleado logístico")
    void testCrearEmpleadoLogistica() {
        EmpleadoLogistica existente = logisticaRepository.findByCorreo("logistica_test@example.com");
        if (existente != null) {
            logisticaRepository.delete(existente);
        }

        EmpleadoLogistica empleado = new EmpleadoLogistica();
        empleado.setNombre("Logístico Uno");
        empleado.setCorreo("logistica_test@example.com");
        empleado.setContraseña("1234");
        empleado.setTelefono(987654321);

        EmpleadoLogistica guardado = logisticaRepository.save(empleado);
        Optional<EmpleadoLogistica> encontrado = logisticaRepository.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("logistica_test@example.com", encontrado.get().getCorreo());
    }

    @Test
    @DisplayName("Actualizar datos de empleado logístico")
    void testActualizarEmpleadoLogistica() {
        EmpleadoLogistica empleado = logisticaRepository.findByCorreo("logistica_actualizar@example.com");
        if (empleado == null) {
            empleado = new EmpleadoLogistica();
            empleado.setNombre("Nombre Antiguo");
            empleado.setCorreo("logistica_actualizar@example.com");
            empleado.setContraseña("1234");
            empleado.setTelefono(111111111);
            empleado = logisticaRepository.save(empleado);
        }

        empleado.setNombre("Nombre Actualizado");
        empleado.setTelefono(222222222);
        EmpleadoLogistica actualizado = logisticaRepository.save(empleado);

        assertNotNull(actualizado);
        assertEquals("Nombre Actualizado", actualizado.getNombre());
        assertEquals(222222222, actualizado.getTelefono());
    }

    @Test
    @DisplayName("Asignar pedido a empleado de logística")
    void testAsignarPedidoAEmpleado() {
        // Crear o buscar empleado
        EmpleadoLogistica empleado = logisticaRepository.findByCorreo("logistica_asignar@example.com");
        if (empleado == null) {
            empleado = new EmpleadoLogistica();
            empleado.setNombre("Empleado Asignar");
            empleado.setCorreo("logistica_asignar@example.com");
            empleado.setContraseña("1234");
            empleado.setTelefono(333333333);
            empleado = logisticaRepository.save(empleado);
        }

        // Crear o buscar cliente
        Cliente cliente = clienteRepository.findByCorreo("cliente_test@example.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setCorreo("cliente_test@example.com");
            cliente.setNombre("Cliente Test");
            cliente.setDireccion("Calle Test 123");
            cliente.setTelefono(123456789);
            cliente.setContraseña("pass123");
            cliente = clienteRepository.save(cliente);
        }

        // Crear pedido
        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        pedido.setCliente(cliente);
        pedido = pedidoRepository.save(pedido);

        // Asignar pedido usando servicio REAL (no mock)
        Pedido pedidoAsignado = logisticaService.asignarPedido(pedido.getId(), empleado.getId());

        // Confirmar que el pedido asignado no es null
        assertNotNull(pedidoAsignado);

        // Confirmar que el empleado logístico fue asignado correctamente
        assertNotNull(pedidoAsignado.getEmpleadoLogistica());
        assertEquals(empleado.getId(), pedidoAsignado.getEmpleadoLogistica().getId());

        // Opcional: volver a buscar en BD y verificar cambios persistidos
        Pedido pedidoBD = pedidoRepository.findById(pedido.getId()).orElse(null);
        assertNotNull(pedidoBD);
        assertNotNull(pedidoBD.getEmpleadoLogistica());
        assertEquals(empleado.getId(), pedidoBD.getEmpleadoLogistica().getId());
    }


    @Test
    @DisplayName("Actualizar estado de un pedido")
    void testActualizarEstadoPedido() {
        // Crear o buscar cliente
        Cliente cliente = clienteRepository.findByCorreo("cliente_test_estado@example.com");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setCorreo("cliente_test_estado@example.com");
            cliente.setNombre("Cliente Estado");
            cliente.setDireccion("Calle Estado 123");
            cliente.setTelefono(987654321);
            cliente.setContraseña("pass456");
            cliente = clienteRepository.save(cliente);
        }

        // Crear pedido
        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        pedido.setCliente(cliente);
        pedido = pedidoRepository.save(pedido);

        // Actualizar estado usando servicio REAL (no mock)
        Pedido pedidoActualizado = logisticaService.actualizarEstadoPedido(pedido.getId(), "Enviado");

        assertNotNull(pedidoActualizado);
        assertEquals("Enviado", pedidoActualizado.getEstado());

        // Opcional: verificar en BD
        Pedido pedidoBD = pedidoRepository.findById(pedido.getId()).orElse(null);
        assertNotNull(pedidoBD);
        assertEquals("Enviado", pedidoBD.getEstado());
    }

}
