package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Administración", description = "Gestión de empleados de logística y ventas")
public class AdministradorController {

    @Autowired
    private AdministradorService adminService;

    @Operation(summary = "Crear empleado de logística")
    @PostMapping("/logistica")
    public EmpleadoLogistica crearLogistica(@RequestBody EmpleadoLogistica empleado) {
        return adminService.crearEmpleadoLogistica(empleado);
    }

    @Operation(summary = "Crear empleado de ventas")
    @PostMapping("/ventas")
    public EmpleadoVentas crearVentas(@RequestBody EmpleadoVentas empleado) {
        return adminService.crearEmpleadoVentas(empleado);
    }

    @Operation(summary = "Actualizar empleado de logística por ID")
    @PutMapping("/logistica/{id}")
    public EmpleadoLogistica actualizarLogistica(@PathVariable Long id, @RequestBody EmpleadoLogistica empleado) {
        return adminService.actualizarEmpleadoLogistica(id, empleado);
    }

    @Operation(summary = "Actualizar empleado de ventas por ID")
    @PutMapping("/ventas/{id}")
    public EmpleadoVentas actualizarVentas(@PathVariable Long id, @RequestBody EmpleadoVentas empleado) {
        return adminService.actualizarEmpleadoVentas(id, empleado);
    }

    @Operation(summary = "Eliminar empleado de logística por ID")
    @DeleteMapping("/logistica/{id}")
    public String eliminarLogistica(@PathVariable Long id) {
        return adminService.eliminarEmpleadoLogistica(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    @Operation(summary = "Eliminar empleado de ventas por ID")
    @DeleteMapping("/ventas/{id}")
    public String eliminarVentas(@PathVariable Long id) {
        return adminService.eliminarEmpleadoVentas(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    @Operation(summary = "Listar todos los empleados de logística")
    @GetMapping("/logistica")
    public List<EmpleadoLogistica> listarLogistica() {
        return adminService.listarEmpleadosLogistica();
    }

    @Operation(summary = "Listar todos los empleados de ventas")
    @GetMapping("/ventas")
    public List<EmpleadoVentas> listarVentas() {
        return adminService.listarEmpleadosVentas();
    }
}
