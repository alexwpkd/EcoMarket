package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.CarroComprasControllerV2;
import com.duoc.EcoMarket.model.CarroCompras;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarroComprasModelAssembler implements RepresentationModelAssembler<CarroCompras, EntityModel<CarroCompras>> {

    @Override
    public EntityModel<CarroCompras> toModel(CarroCompras carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarroComprasControllerV2.class).obtenerCarrito(carrito.getCliente().getCorreo())).withSelfRel(),
                linkTo(methodOn(CarroComprasControllerV2.class).vaciarCarrito(carrito.getCliente().getCorreo())).withRel("vaciar"),
                linkTo(methodOn(CarroComprasControllerV2.class).total(carrito.getCliente().getCorreo())).withRel("total"));
    }
}
