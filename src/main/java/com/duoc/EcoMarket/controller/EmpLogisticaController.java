package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.services.LogisticaService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado-logistica")
public class EmpLogisticaController {

    @Autowired
    private LogisticaService LS;

    // Crear nuevo empleado de logística
    @PostMapping("/crear")
    public EmpleadoLogistica crearEmpleado(@RequestBody EmpleadoLogistica empleado) {
        return LS.crearEmpleado(empleado);
    }

    // Actualizar información del empleado
    @PutMapping("/actualizar/{id}")
    public EmpleadoLogistica actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoLogistica datos) {
        return LS.actualizarEmpleado(id, datos);
    }

    // Ver pedidos asignados a un empleado
    @GetMapping("/pedidos/{empleadoId}")
    public List<Pedido> obtenerPedidosAsignados(@PathVariable Long empleadoId) {
        return LS.obtenerPedidosAsignados(empleadoId);
    }

    // Asignar un pedido a un empleado
    @PutMapping("/asignar-pedido")
    public Pedido asignarPedido(@RequestParam Long pedidoId, @RequestParam Long empleadoId) {
        return LS.asignarPedido(pedidoId, empleadoId);
    }

    // Cambiar estado del pedido
    @PutMapping("/actualizar-estado")
    public Pedido actualizarEstado(@RequestParam Long pedidoId, @RequestParam String nuevoEstado) {
        return LS.actualizarEstadoPedido(pedidoId, nuevoEstado);
    }
}
