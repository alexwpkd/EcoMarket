package com.duoc.EcoMarket;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import com.duoc.EcoMarket.services.ClienteService;
import com.duoc.EcoMarket.controller.ClienteController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//Importaciones del profe ??
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService cls;

    @MockitoBean
    private ClienteRepository clr;

    @Test
    public void contextLoads() {
        // Aquí iría tu prueba
    }
}