package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LogisticaRepository {

    private List<EmpleadoLogistica> empLogistica = new ArrayList<>();

    public List<EmpleadoLogistica> obtenerEmpLogistica() {
        return empLogistica;
    }

    public EmpleadoLogistica guardarEmpleado(EmpleadoLogistica emp) {
        empLogistica.add(emp);
        return emp;
    }

    public EmpleadoLogistica actualizarEmpleado(EmpleadoLogistica emp) {
        for (int i = 0; i < empLogistica.size(); i++) {
            if (empLogistica.get(i).getCorreo().equals(emp.getCorreo())) {
                empLogistica.set(i, emp);
                return emp;
            }
        }
        return null;
    }

    public EmpleadoLogistica buscarPorCorreo(String correo) {
        for (EmpleadoLogistica emp : empLogistica) {
            if (emp.getCorreo().equals(correo)) {
                return emp;
            }
        }
        return null;
    }

    public String eliminarEmpleado(String correo) {
        EmpleadoLogistica emp = buscarPorCorreo(correo);
        if (emp != null) {
            empLogistica.remove(emp);
            return "Empleado de logística eliminado";
        }
        return "No se encontró empleado de logística";
    }

    public EmpleadoLogistica iniciarSesion(String correo, String contrasena) {
        EmpleadoLogistica emp = buscarPorCorreo(correo);
        if (emp != null && emp.getContraseña().equals(contrasena)) {
            return emp;
        }
        return null;
    }
}