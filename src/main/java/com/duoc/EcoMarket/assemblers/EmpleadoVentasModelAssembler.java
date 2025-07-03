package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.EmpVentasControllerV2;
import com.duoc.EcoMarket.model.EmpleadoVentas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoVentasModelAssembler implements RepresentationModelAssembler<EmpleadoVentas, EntityModel<EmpleadoVentas>> {

    @Override
    public EntityModel<EmpleadoVentas> toModel(EmpleadoVentas empleado) {
        return EntityModel.of(empleado,
                linkTo(methodOn(EmpVentasControllerV2.class).obtenerEmpleado(empleado.getId())).withSelfRel(),
                linkTo(methodOn(EmpVentasControllerV2.class).obtenerTodos()).withRel("todos"));
    }
}
