package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.EmpleadoVentasModelAssembler;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import com.duoc.EcoMarket.services.EmpleadoVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/empleado-ventas")
public class EmpVentasControllerV2 {

    @Autowired
    private EmpleadoVentasService empleadoService;

    @Autowired
    private EmpleadoVentasModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<EmpleadoVentas>> obtenerTodos() {
        List<EntityModel<EmpleadoVentas>> empleados = empleadoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(empleados,
                linkTo(methodOn(EmpVentasControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<EmpleadoVentas> obtenerEmpleado(@PathVariable Long id) {
        EmpleadoVentas empleado = empleadoService.obtenerPorId(id);
        return assembler.toModel(empleado);
    }
}
