package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.VentaControllerV2;
import com.duoc.EcoMarket.model.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {

    @Override
    public EntityModel<Venta> toModel(Venta venta) {
        return EntityModel.of(venta,
                linkTo(methodOn(VentaControllerV2.class).generarFactura(venta.getId())).withRel("factura"),
                linkTo(methodOn(VentaControllerV2.class).consultarInventario()).withRel("inventario"));
    }
}
