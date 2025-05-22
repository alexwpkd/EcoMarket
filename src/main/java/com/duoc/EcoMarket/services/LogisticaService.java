package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.repository.LogisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticaService {
    @Autowired
    private LogisticaRepository LogisticaService;

    public List<EmpleadoLogistica> getEmpleadosLogistica() {
        return LogisticaService.obtenerEmpLogistica();
    }

    public EmpleadoLogistica guardar(EmpleadoLogistica emp) {
        return LogisticaService.guardarEmpleado(emp);
    }

    public EmpleadoLogistica actualizar(EmpleadoLogistica emp) {
        return LogisticaService.actualizarEmpleado(emp);
    }

    public EmpleadoLogistica buscar(String correo) {
        return LogisticaService.buscarPorCorreo(correo);
    }

    public String eliminar(String correo) {
        return LogisticaService.eliminarEmpleado(correo);
    }

    public EmpleadoLogistica inicioSesion(String correo, String contrasena) {
        return LogisticaService.iniciarSesion(correo, contrasena);
    }
}