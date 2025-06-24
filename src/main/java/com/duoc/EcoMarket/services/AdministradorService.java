package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.repository.EmpleadoLogisticaRepository;
import com.duoc.EcoMarket.repository.EmpleadoVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private EmpleadoLogisticaRepository logisticaRepository;

    @Autowired
    private EmpleadoVentasRepository ventasRepository;


    public EmpleadoLogistica crearEmpleadoLogistica(EmpleadoLogistica empleado) {
        return logisticaRepository.save(empleado);
    }


    public EmpleadoVentas crearEmpleadoVentas(EmpleadoVentas empleado) {
        return ventasRepository.save(empleado);
    }


    public EmpleadoLogistica actualizarEmpleadoLogistica(Long id, EmpleadoLogistica datosActualizados) {
        EmpleadoLogistica existente = logisticaRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(datosActualizados.getNombre());
            existente.setCorreo(datosActualizados.getCorreo());
            existente.setTelefono(datosActualizados.getTelefono());
            return logisticaRepository.save(existente);
        }
        return null;
    }


    public EmpleadoVentas actualizarEmpleadoVentas(Long id, EmpleadoVentas datosActualizados) {
        EmpleadoVentas existente = ventasRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(datosActualizados.getNombre());
            existente.setCorreo(datosActualizados.getCorreo());
            existente.setTelefono(datosActualizados.getTelefono());
            return ventasRepository.save(existente);
        }
        return null;
    }


    public boolean eliminarEmpleadoLogistica(Long id) {
        if (logisticaRepository.existsById(id)) {
            logisticaRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public boolean eliminarEmpleadoVentas(Long id) {
        if (ventasRepository.existsById(id)) {
            ventasRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<EmpleadoLogistica> listarEmpleadosLogistica() {
        return logisticaRepository.findAll();
    }


    public List<EmpleadoVentas> listarEmpleadosVentas() {
        return ventasRepository.findAll();
    }
}
