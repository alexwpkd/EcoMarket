package com.duoc.EcoMarket.controllers;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.EmpleadoVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleado-ventas")
public class EmpVentasController {

    @Autowired
    private EmpleadoVentasService EVS;

    @GetMapping("/{id}")
    public EmpleadoVentas obtenerEmpleado(@PathVariable Long id) {
        EmpleadoVentas empleado = EVS.obtenerPorId(id);
        if (empleado == null) {
            throw new RuntimeException("Empleado de Ventas no encontrado");
        }
        return empleado;
    }
}
