package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService adminService;

    //  Crear empleado de logística
    @PostMapping("/logistica")
    public EmpleadoLogistica crearLogistica(@RequestBody EmpleadoLogistica empleado) {
        return adminService.crearEmpleadoLogistica(empleado);
    }

    //  Crear empleado de ventas
    @PostMapping("/ventas")
    public EmpleadoVentas crearVentas(@RequestBody EmpleadoVentas empleado) {
        return adminService.crearEmpleadoVentas(empleado);
    }

    //  Actualizar empleado de logística
    @PutMapping("/logistica/{id}")
    public EmpleadoLogistica actualizarLogistica(@PathVariable Long id, @RequestBody EmpleadoLogistica empleado) {
        return adminService.actualizarEmpleadoLogistica(id, empleado);
    }

    //  Actualizar empleado de ventas
    @PutMapping("/ventas/{id}")
    public EmpleadoVentas actualizarVentas(@PathVariable Long id, @RequestBody EmpleadoVentas empleado) {
        return adminService.actualizarEmpleadoVentas(id, empleado);
    }

    //  Eliminar empleado de logística
    @DeleteMapping("/logistica/{id}")
    public String eliminarLogistica(@PathVariable Long id) {
        return adminService.eliminarEmpleadoLogistica(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    //  Eliminar empleado de ventas
    @DeleteMapping("/ventas/{id}")
    public String eliminarVentas(@PathVariable Long id) {
        return adminService.eliminarEmpleadoVentas(id) ? "Eliminado correctamente" : "Empleado no encontrado";
    }

    //  Listar empleados de logística
    @GetMapping("/logistica")
    public List<EmpleadoLogistica> listarLogistica() {
        return adminService.listarEmpleadosLogistica();
    }

    //  Listar empleados de ventas
    @GetMapping("/ventas")
    public List<EmpleadoVentas> listarVentas() {
        return adminService.listarEmpleadosVentas();
    }
}
