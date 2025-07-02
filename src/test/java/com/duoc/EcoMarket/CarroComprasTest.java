package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.repository.CarroComprasRepository;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.services.CarroComprasService;
import com.duoc.EcoMarket.services.ProductoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarroComprasTest {

    @Autowired
    CarroComprasRepository carroComprasRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @MockitoBean
    ProductoService productoServiceMock;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarroComprasService carroComprasServiceMock;

    @Test
    @DisplayName("Obtener carrito nuevo si no existe")
    void testObtenerNuevoCarrito() {
        // Asegurarse de que el cliente no exista previamente
        Cliente existente = clienteRepository.findByCorreo("carrito_test@example.com");
        if (existente != null) {
            carroComprasRepository.findByCliente(existente).ifPresent(carrito -> {
                carroComprasRepository.delete(carrito);
            });
            clienteRepository.delete(existente);
        }
        // Crear cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Test");
        cliente.setCorreo("carrito_test@example.com");
        cliente.setDireccion("Av. Siempre Viva");
        cliente.setTelefono(123456789);
        cliente.setContraseña("1234");
        cliente = clienteRepository.save(cliente);

        // Borrar carrito si ya existe
        carroComprasRepository.findByCliente(cliente).ifPresent(carro -> carroComprasRepository.delete(carro));

        // Servicio real
        CarroComprasService servicio = new CarroComprasService();
        // Inyección manual de repos
        try {
            var field1 = CarroComprasService.class.getDeclaredField("ccRepository");
            field1.setAccessible(true);
            field1.set(servicio, carroComprasRepository);

            var field2 = CarroComprasService.class.getDeclaredField("clienteRepository");
            field2.setAccessible(true);
            field2.set(servicio, clienteRepository);
        } catch (Exception e) {
            fail("Fallo la inyección de dependencias");
        }

        CarroCompras carrito = servicio.obtener("carrito_test@example.com");

        assertNotNull(carrito);
        assertEquals(cliente.getId(), carrito.getCliente().getId());
    }

    @Test
    @DisplayName("Test Controller - Obtener total del carrito")
    void testControllerObtenerTotalCarrito() {
        when(carroComprasServiceMock.total("carrito_total@example.com")).thenReturn(19990.0);
        try {
            mockMvc.perform(get("/carrito/carrito_total@example.com/total"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("19990.0"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Test Controller - Agregar producto al carrito")
    void testControllerAgregarProductoAlCarrito() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setPrecio(10000.0);

        when(productoServiceMock.buscarPorId(1L)).thenReturn(Optional.of(producto));
        when(carroComprasServiceMock.obtener("cliente@correo.com")).thenReturn(new CarroCompras());

        try {
            mockMvc.perform(
                            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                    .post("/carrito/cliente@correo.com/agregar/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Producto agregado al carrito"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    @DisplayName("Test Controller - Vaciar carrito")
    void testControllerVaciarCarrito() {
        try {
            mockMvc.perform(
                            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                    .delete("/carrito/cliente@correo.com/vaciar"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Carrito vaciado"));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}
