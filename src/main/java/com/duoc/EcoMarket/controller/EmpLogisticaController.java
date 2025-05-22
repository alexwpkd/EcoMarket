package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.services.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/empleadoLogistica")
public class EmpLogisticaController {

    @Autowired
    private LogisticaService LogService;

    @GetMapping
    public List<EmpleadoLogistica> listar() {
        return LogService.getEmpleadosLogistica();
    }

    @PostMapping
    public EmpleadoLogistica guardar(@RequestBody EmpleadoLogistica emp) {
        return LogService.guardar(emp);
    }

    @PutMapping("{correo}")
    public EmpleadoLogistica actualizar(@PathVariable String correo, @RequestBody EmpleadoLogistica emp) {
        return LogService.actualizar(emp);
    }

    @DeleteMapping("{correo}")
    public String eliminar(@PathVariable String correo) {
        return LogService.eliminar(correo);
    }

    @GetMapping("{correo}")
    public EmpleadoLogistica buscar(@PathVariable String correo) {
        return LogService.buscar(correo);
    }

    @PostMapping("/login")
    public EmpleadoLogistica login(@RequestBody EmpleadoLogistica emp) {
        return LogService.inicioSesion(emp.getCorreo(), emp.getContraseña());
    }
}