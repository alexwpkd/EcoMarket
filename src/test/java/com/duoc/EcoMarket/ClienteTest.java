package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.services.ClienteService;
import com.duoc.EcoMarket.controller.ClienteController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteTest {

    @Autowired
    ClienteRepository ClienteRepository;

    @MockitoBean
    MockMvc mockMvc;

    @MockitoBean
    ClienteService ClienteServiceMock;

    @Test
    @DisplayName("FindAll Clientes")
    public void testObtenerTodosClientes() {
        List<Cliente> clientes = ClienteRepository.findAll();
        assertNotNull(clientes);
        // Puedes ajustar esta validación según la cantidad esperada en tu BD de prueba
            assertTrue(clientes.size() >= 0);
        }


    @Test
    @DisplayName("Buscar cliente y validar correo")
    void testBuscarClientePorId() {
        Optional<Cliente> clienteOpt = ClienteRepository.findById(1L);
        assertTrue(clienteOpt.isPresent());
        Cliente cliente = clienteOpt.get();
        assertNotNull(cliente.getCorreo());
    }

    @Test
    @DisplayName("Test Controller Obtener lista de clientes")
    void testControllerObtenerClientes() {
        when(ClienteServiceMock.obtenerTodos()).thenReturn(List.of(new Cliente()));
        try {
            mockMvc.perform(get("/api/v1/cliente"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}