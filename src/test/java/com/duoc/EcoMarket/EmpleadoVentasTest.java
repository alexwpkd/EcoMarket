package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.repository.EmpleadoVentasRepository;
import com.duoc.EcoMarket.services.EmpleadoVentasService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpleadoVentasTest {

    @Autowired
    private EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private EmpleadoVentasService empleadoVentasService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Guardar y buscar empleado de ventas por correo")
    void testGuardarYBuscarPorCorreo() {
        String correo = "empleado_ventas@correo.com";

        EmpleadoVentas existente = empleadoVentasRepository.findByCorreo(correo);
        if (existente == null) {
            EmpleadoVentas nuevo = new EmpleadoVentas();
            nuevo.setNombre("Empleado Ventas");
            nuevo.setCorreo(correo);
            nuevo.setContraseña("abc123");
            nuevo.setTelefono(987654321);
            empleadoVentasRepository.save(nuevo);
        }

        EmpleadoVentas buscado = empleadoVentasRepository.findByCorreo(correo);
        assertNotNull(buscado);
        assertEquals(correo, buscado.getCorreo());
    }

    @Test
    @DisplayName("Obtener empleado de ventas por ID desde el servicio")
    void testObtenerEmpleadoPorId() {
        EmpleadoVentas empleado = new EmpleadoVentas();
        empleado.setNombre("Empleado Servicio");
        empleado.setCorreo("empleado_servicio@correo.com");
        empleado.setContraseña("123");
        empleado.setTelefono(77777777);
        empleado = empleadoVentasRepository.save(empleado);

        EmpleadoVentas resultado = empleadoVentasService.obtenerPorId(empleado.getId());
        assertNotNull(resultado);
        assertEquals(empleado.getId(), resultado.getId());
    }

    @Test
    @DisplayName("Obtener empleado de ventas por ID desde el controlador")
    void testObtenerEmpleadoDesdeController() throws Exception {
        EmpleadoVentas empleado = new EmpleadoVentas();
        empleado.setNombre("Empleado Controlador");
        empleado.setCorreo("empleado_controlador@correo.com");
        empleado.setContraseña("123456");
        empleado.setTelefono(66666666);
        empleado = empleadoVentasRepository.save(empleado);

        mockMvc.perform(get("/empleado-ventas/" + empleado.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("empleado_controlador@correo.com"));
    }

    @Test
    @DisplayName("Obtener empleado con ID válido no debe retornar null")
    void testNoRetornaNullConIdValido() {
        EmpleadoVentas empleado = new EmpleadoVentas();
        empleado.setNombre("Empleado Null");
        empleado.setCorreo("empleado_null@correo.com");
        empleado.setContraseña("null123");
        empleado.setTelefono(55555555);
        empleado = empleadoVentasRepository.save(empleado);

        EmpleadoVentas obtenido = empleadoVentasService.obtenerPorId(empleado.getId());
        assertNotNull(obtenido);
    }
}
