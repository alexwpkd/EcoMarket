package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/empleadoVentas")
public class EmpVentasController {

    @Autowired
    private VentasService Empservice;

    @GetMapping
    public List<EmpleadoVentas> listar() {
        return Empservice.getEmpleadosVentas();
    }

    @PostMapping
    public EmpleadoVentas guardar(@RequestBody EmpleadoVentas emp) {
        return Empservice.guardarEmpVentas(emp);
    }

    @PutMapping("{correo}")
    public EmpleadoVentas actualizar(@PathVariable String correo, @RequestBody EmpleadoVentas emp) {
        return Empservice.actualizarEmpVenta(emp);
    }

    @DeleteMapping("{correo}")
    public String eliminar(@PathVariable String correo) {
        return Empservice.eliminar(correo);
    }

    @GetMapping("{correo}")
    public EmpleadoVentas buscar(@PathVariable String correo) {
        return Empservice.buscarEmpVenta(correo);
    }

    @PostMapping("/login")
    public EmpleadoVentas login(@RequestBody EmpleadoVentas emp) {
        return Empservice.login(emp.getCorreo(), emp.getContraseña());
    }
}