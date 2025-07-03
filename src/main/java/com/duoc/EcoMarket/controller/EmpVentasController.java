package com.duoc.EcoMarket.controllers;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.EmpleadoVentasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleado-ventas")
@Tag(name = "Empleado Ventas", description = "Operaciones del personal de ventas")
public class EmpVentasController {

    @Autowired
    private EmpleadoVentasService EVS;

    @Operation(summary = "Obtener un empleado de ventas por ID")
    @GetMapping("/{id}")
    public EmpleadoVentas obtenerEmpleado(@PathVariable Long id) {
        EmpleadoVentas empleado = EVS.obtenerPorId(id);
        if (empleado == null) {
            throw new RuntimeException("Empleado de Ventas no encontrado");
        }
        return empleado;
    }
}
