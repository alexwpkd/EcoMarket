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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;



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

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ClienteService ClienteServiceMock;

    @Test
    @DisplayName("Buscar cliente y validar correo")
    void testBuscarClientePorId() {
        Cliente existente = ClienteRepository.findByCorreo("test@example.com");
        if (existente != null) {
            ClienteRepository.delete(existente);
        }
        Cliente cliente = new Cliente();
        cliente.setNombre("Test");
        cliente.setCorreo("test@example.com");
        cliente.setDireccion("Av. Siempre Viva");
        cliente.setTelefono(123456789);
        cliente.setContraseña("1234");
        cliente = ClienteRepository.save(cliente);
        Optional<Cliente> clienteOpt = ClienteRepository.findById(cliente.getId());
        assertTrue(clienteOpt.isPresent());
    }

    @Test
    @DisplayName("Actualizar perfil de cliente")
    void testActualizarPerfilCliente() {
        // Cliente original con correo único
        Cliente cliente = new Cliente();
        cliente.setNombre("Test");
        cliente.setCorreo("test_update@example.com");
        cliente.setDireccion("Av. Siempre Viva");
        cliente.setTelefono(123456789);
        cliente.setContraseña("1234");
        cliente = ClienteRepository.save(cliente);

        // Nuevos datos para actualizar
        Cliente nuevosDatos = new Cliente();
        nuevosDatos.setNombre("Test");
        nuevosDatos.setCorreo("test_update@example.com"); // Mismo correo
        nuevosDatos.setDireccion("Nueva Dirección 456");
        nuevosDatos.setTelefono(123456789);
        nuevosDatos.setContraseña("1234");

        ClienteService servicio = new ClienteService(ClienteRepository);
        Cliente actualizado = servicio.actualizarPerfil(cliente.getId(), nuevosDatos);

        assertNotNull(actualizado);
        assertEquals("Nueva Dirección 456", actualizado.getDireccion());
    }

    @Test
    @DisplayName("Fallo al iniciar sesión con contraseña incorrecta")
    void testLoginContrasenaIncorrecta() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test");
        cliente.setCorreo("test_login_fail@example.com"); // Correo único
        cliente.setDireccion("Av. Siempre Viva");
        cliente.setTelefono(123456789);
        cliente.setContraseña("1234");
        ClienteRepository.save(cliente);

        ClienteService servicio = new ClienteService(ClienteRepository);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicio.iniciarSesion("test_login_fail@example.com", "contraseña_mal");
        });

        assertEquals("Correo o contraseña incorrectos.", exception.getMessage());
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