package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.repository.EmpleadoLogisticaRepository;
import com.duoc.EcoMarket.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LogisticaService {

    @Autowired
    private EmpleadoLogisticaRepository ELR;

    @Autowired
    private PedidoRepository PR;


    public EmpleadoLogistica crearEmpleado(EmpleadoLogistica empleado) {
        return ELR.save(empleado);
    }

    public EmpleadoLogistica buscarPorCorreo(String correo) {
        return ELR.findByCorreo(correo);
    }


    public EmpleadoLogistica actualizarEmpleado(Long id, EmpleadoLogistica datosActualizados) {
        EmpleadoLogistica existente = ELR.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(datosActualizados.getNombre());
            existente.setCorreo(datosActualizados.getCorreo());
            existente.setContraseña(datosActualizados.getContraseña());
            existente.setTelefono(datosActualizados.getTelefono());
            return ELR.save(existente);
        }
        return null;
    }


    @Transactional
    public List<Pedido> obtenerPedidosAsignados(Long empleadoId) {
        EmpleadoLogistica empleado = ELR.findById(empleadoId).orElse(null);
        return (empleado != null) ? empleado.getPedidos() : null;
    }


    public Pedido asignarPedido(Long pedidoId, Long empleadoId) {
        Pedido pedido = PR.findById(pedidoId).orElse(null);
        EmpleadoLogistica empleado = ELR.findById(empleadoId).orElse(null);
        if (pedido != null && empleado != null) {
            pedido.setEmpleadoLogistica(empleado);
            return PR.save(pedido);
        }
        return null;
    }


    public Pedido actualizarEstadoPedido(Long pedidoId, String nuevoEstado) {
        Pedido pedido = PR.findById(pedidoId).orElse(null);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            return PR.save(pedido);
        }
        return null;
    }
}
