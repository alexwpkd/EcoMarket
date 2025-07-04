package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.services.LogisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado-logistica")
@Tag(name = "Empleado Logística", description = "Operaciones del personal de logística")
public class EmpLogisticaController {

    @Autowired
    private LogisticaService LS;

    @Operation(summary = "Crear empleado de logística")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/crear")
    public EmpleadoLogistica crearEmpleado(@RequestBody EmpleadoLogistica empleado) {
        return LS.crearEmpleado(empleado);
    }

    @Operation(summary = "Actualizar datos del empleado de logística por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado actualizado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public EmpleadoLogistica actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoLogistica datos) {
        return LS.actualizarEmpleado(id, datos);
    }

    @Operation(summary = "Obtener pedidos asignados a un empleado de logística")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos obtenidos correctamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/pedidos/{empleadoId}")
    public List<Pedido> obtenerPedidosAsignados(@PathVariable Long empleadoId) {
        return LS.obtenerPedidosAsignados(empleadoId);
    }

    @Operation(summary = "Asignar un pedido a un empleado de logística")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido asignado correctamente"),
            @ApiResponse(responseCode = "404", description = "Empleado o pedido no encontrado")
    })
    @PutMapping("/asignar-pedido")
    public Pedido asignarPedido(@RequestParam Long pedidoId, @RequestParam Long empleadoId) {
        return LS.asignarPedido(pedidoId, empleadoId);
    }

    @Operation(summary = "Actualizar estado de un pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @PutMapping("/actualizar-estado")
    public Pedido actualizarEstado(@RequestParam Long pedidoId, @RequestParam String nuevoEstado) {
        return LS.actualizarEstadoPedido(pedidoId, nuevoEstado);
    }
}
