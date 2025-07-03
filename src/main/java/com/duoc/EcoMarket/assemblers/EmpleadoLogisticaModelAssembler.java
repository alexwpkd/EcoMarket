package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.EmpLogisticaControllerV2;
import com.duoc.EcoMarket.model.EmpleadoLogistica;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoLogisticaModelAssembler implements RepresentationModelAssembler<EmpleadoLogistica, EntityModel<EmpleadoLogistica>> {

    @Override
    public EntityModel<EmpleadoLogistica> toModel(EmpleadoLogistica empleado) {
        return EntityModel.of(empleado,
                linkTo(methodOn(EmpLogisticaControllerV2.class).obtenerEmpleado(empleado.getId())).withSelfRel(),
                linkTo(methodOn(EmpLogisticaControllerV2.class).actualizarEmpleado(empleado.getId(), empleado)).withRel("actualizar"),
                linkTo(methodOn(EmpLogisticaControllerV2.class).obtenerTodos()).withRel("todos"));
    }


}
