package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.PedidoControllerV2;
import com.duoc.EcoMarket.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).obtenerPorId(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).actualizarEstado(pedido.getId(), pedido.getEstado())).withRel("actualizarEstado"),
                linkTo(methodOn(PedidoControllerV2.class).listarPedidos()).withRel("listar"));
    }
}
