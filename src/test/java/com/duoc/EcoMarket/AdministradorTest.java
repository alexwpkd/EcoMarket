package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.repository.EmpleadoLogisticaRepository;
import com.duoc.EcoMarket.repository.EmpleadoVentasRepository;
import com.duoc.EcoMarket.services.AdministradorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AdministradorTest {

    @Autowired
    EmpleadoLogisticaRepository logisticaRepository;

    @Autowired
    EmpleadoVentasRepository ventasRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AdministradorService adminServiceMock;

    @Test
    @DisplayName("Crear y buscar empleado de logística")
    void testCrearYBuscarEmpleadoLogistica() {
        EmpleadoLogistica existente = logisticaRepository.findByCorreo("logistica_test@example.com");
        if (existente != null) {
            logisticaRepository.delete(existente);
        }

        EmpleadoLogistica empleado = new EmpleadoLogistica();
        empleado.setNombre("Logistica Test");
        empleado.setCorreo("logistica_test@example.com");
        empleado.setTelefono(123456789);
        empleado.setContraseña("1234");  // <-- IMPORTANTE asignar contraseña
        empleado = logisticaRepository.save(empleado);


        Optional<EmpleadoLogistica> empleadoOpt = logisticaRepository.findById(empleado.getId());
        assertTrue(empleadoOpt.isPresent());
        assertEquals("logistica_test@example.com", empleadoOpt.get().getCorreo());
    }

    @Test
    @DisplayName("Actualizar empleado de ventas")
    void testActualizarEmpleadoVentas() {
        EmpleadoVentas empleado = new EmpleadoVentas();
        empleado.setNombre("Ventas Test");
        empleado.setCorreo("ventas_test@example.com");
        empleado.setTelefono(987654321);
        empleado = ventasRepository.save(empleado);

        EmpleadoVentas nuevosDatos = new EmpleadoVentas();
        nuevosDatos.setNombre("Ventas Actualizado");
        nuevosDatos.setCorreo("ventas_test@example.com");
        nuevosDatos.setTelefono(111222333);

        AdministradorService servicio = new AdministradorService(logisticaRepository, ventasRepository);
        EmpleadoVentas actualizado = servicio.actualizarEmpleadoVentas(empleado.getId(), nuevosDatos);

        assertNotNull(actualizado);
        assertEquals("Ventas Actualizado", actualizado.getNombre());
    }

    @Test
    @DisplayName("Test Controller - Obtener empleados de logística")
    void testControllerObtenerLogistica() {
        EmpleadoLogistica e1 = new EmpleadoLogistica();
        e1.setNombre("Log Juan");

        when(adminServiceMock.listarEmpleadosLogistica()).thenReturn(List.of(e1));
        try {
            mockMvc.perform(get("/api/admin/logistica"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
