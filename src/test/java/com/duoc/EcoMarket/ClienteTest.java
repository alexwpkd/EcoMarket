package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.services.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//Importaciones del profe ??
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteServiceTest {

    @Autowired
    private ClienteService cls;

    @MockitoBean
    private ClienteRepository clr;

    @MockitoBean
    MockMvc mockMvc;

    @MockitoBean
    ClienteService ClienteServiceMock;


    @Test
    @DisplayName("FindAll Clientes")
    public void testObtenerTodosClientes() {

        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        // Puedes ajustar esta validación según la cantidad esperada en tu BD de prueba
            assertTrue(clientes.size() >= 0);
        }

        @Test
        @DisplayName("Buscar cliente y validar correo")
        void testBuscarClientePorId() {
            Optional<Cliente> clienteOpt = clienteRepository.findById(1L);
            assertTrue(clienteOpt.isPresent());

            Cliente cliente = clienteOpt.get();
            assertNotNull(cliente.getCorreo());
        }

        @Test
        @DisplayName("Test Controller - Obtener lista de clientes")
        void testControllerObtenerClientes() {
            when(clienteServiceMock.obtenerTodos()).thenReturn(List.of(new Cliente()));

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
}