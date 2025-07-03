package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.repository.EmpleadoVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class EmpleadoVentasService {

    @Autowired
    private EmpleadoVentasRepository EVR;

    public EmpleadoVentas obtenerPorId(Long id) {
        Optional<EmpleadoVentas> empleado = EVR.findById(id);
        return empleado.orElse(null);
    }

    public List<EmpleadoVentas> obtenerTodos() {
        return EVR.findAll();
    }


}
