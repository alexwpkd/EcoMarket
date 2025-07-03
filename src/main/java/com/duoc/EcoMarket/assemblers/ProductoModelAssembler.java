package com.duoc.EcoMarket.assemblers;

import com.duoc.EcoMarket.controllerv2.ProductoControllerV2;
import com.duoc.EcoMarket.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).buscarPorId(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).listarProductos()).withRel("listar"),
                linkTo(methodOn(ProductoControllerV2.class).buscarPorCategoria(producto.getCategoria())).withRel("porCategoria"));
    }
}
