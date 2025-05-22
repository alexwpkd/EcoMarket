package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasService {
    @Autowired
    private VentasRepository VtasRepository;

    public List<EmpleadoVentas> getEmpleadosVentas() {
        return VtasRepository.obtenerEmpVentas();
    }

    public EmpleadoVentas guardarEmpVentas(EmpleadoVentas emp) {
        return VtasRepository.guardarEmpleadoV(emp);
    }

    public EmpleadoVentas actualizarEmpVenta(EmpleadoVentas emp) {
        return VtasRepository.actualizarEmpleado(emp);
    }

    public EmpleadoVentas buscarEmpVenta(String correo) {
        return VtasRepository.buscarPorCorreo(correo);
    }

    public String eliminar(String correo) {
        return VtasRepository.eliminarEmpleado(correo);
    }

    public EmpleadoVentas login(String correo, String contrasena) {
        return VtasRepository.inicioSesion(correo, contrasena);
    }
}