package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.ClienteControllerV2;
import com.duoc.EcoMarket.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteControllerV2.class).obtenerCliente(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).actualizarPerfil(cliente.getId(), cliente)).withRel("actualizar"),
                linkTo(methodOn(ClienteControllerV2.class).obtenerTodos()).withRel("todos"));
    }
}
