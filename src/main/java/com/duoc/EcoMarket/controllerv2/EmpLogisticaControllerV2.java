package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.EmpleadoLogisticaModelAssembler;
import com.duoc.EcoMarket.model.EmpleadoLogistica;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.services.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/empleado-logistica")
public class EmpLogisticaControllerV2 {

    @Autowired
    private LogisticaService logisticaService;

    @Autowired
    private EmpleadoLogisticaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<EmpleadoLogistica>> obtenerTodos() {
        List<EntityModel<EmpleadoLogistica>> empleados = logisticaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(empleados,
                linkTo(methodOn(EmpLogisticaControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<EmpleadoLogistica> obtenerEmpleado(@PathVariable Long id) {
        EmpleadoLogistica empleado = logisticaService.obtenerPorId(id);
        return assembler.toModel(empleado);
    }

    @GetMapping("/pedidos/{empleadoId}")
    public List<Pedido> obtenerPedidosAsignados(@PathVariable Long empleadoId) {
        return logisticaService.obtenerPedidosAsignados(empleadoId);
    }

    @PutMapping("/asignar-pedido")
    public Pedido asignarPedido(@RequestParam Long pedidoId, @RequestParam Long empleadoId) {
        return logisticaService.asignarPedido(pedidoId, empleadoId);
    }

    @PutMapping("/actualizar-estado")
    public Pedido actualizarEstado(@RequestParam Long pedidoId, @RequestParam String nuevoEstado) {
        return logisticaService.actualizarEstadoPedido(pedidoId, nuevoEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EmpleadoLogistica>> actualizarEmpleado(
            @PathVariable Long id,
            @RequestBody EmpleadoLogistica datosActualizados) {

        EmpleadoLogistica actualizado = logisticaService.actualizarEmpleado(id, datosActualizados);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<EmpleadoLogistica> recurso = assembler.toModel(actualizado);
        return ResponseEntity.ok(recurso);
    }

}
