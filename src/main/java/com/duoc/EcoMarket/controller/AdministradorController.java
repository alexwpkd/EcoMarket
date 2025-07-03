package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado de logística creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/logistica")
    public EmpleadoLogistica crearLogistica(@RequestBody EmpleadoLogistica empleado) {
        return adminService.crearEmpleadoLogistica(empleado);
    }

    @Operation(summary = "Crear empleado de ventas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado de ventas creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/ventas")
    public EmpleadoVentas crearVentas(@RequestBody EmpleadoVentas empleado) {
        return adminService.crearEmpleadoVentas(empleado);
    }

    @Operation(summary = "Actualizar empleado de logística por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @PutMapping("/logistica/{id}")
    public EmpleadoLogistica actualizarLogistica(@PathVariable Long id, @RequestBody EmpleadoLogistica empleado) {
        return adminService.actualizarEmpleadoLogistica(id, empleado);
    }

    @Operation(summary = "Actualizar empleado de ventas por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @PutMapping("/ventas/{id}")
    public EmpleadoVentas actualizarVentas(@PathVariable Long id, @RequestBody EmpleadoVentas empleado) {
        return adminService.actualizarEmpleadoVentas(id, empleado);
    }

    @Operation(summary = "Eliminar empleado de logística por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado eliminado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @DeleteMapping("/logistica/{id}")
    public String eliminarLogistica(@PathVariable Long id) {
        return adminService.eliminarEmpleadoLogistica(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    @Operation(summary = "Eliminar empleado de ventas por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado eliminado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @DeleteMapping("/ventas/{id}")
    public String eliminarVentas(@PathVariable Long id) {
        return adminService.eliminarEmpleadoVentas(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    @Operation(summary = "Listar todos los empleados de logística")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de empleados retornada")
    })
    @GetMapping("/logistica")
    public List<EmpleadoLogistica> listarLogistica() {
        return adminService.listarEmpleadosLogistica();
    }

    @Operation(summary = "Listar todos los empleados de ventas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de empleados retornada")
    })
    @GetMapping("/ventas")
    public List<EmpleadoVentas> listarVentas() {
        return adminService.listarEmpleadosVentas();
    }
}
