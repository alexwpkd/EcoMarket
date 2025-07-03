package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.VentaModelAssembler;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.model.Venta;
import com.duoc.EcoMarket.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/ventas")
public class VentaControllerV2 {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler assembler;

    @GetMapping("/inventario")
    public CollectionModel<EntityModel<Producto>> consultarInventario() {
        List<EntityModel<Producto>> inventario = ventaService.consultarInventario().stream()
                .map(producto -> EntityModel.of(producto,
                        linkTo(methodOn(VentaControllerV2.class).consultarInventario()).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(inventario);
    }

    @GetMapping("/{ventaId}/factura")
    public EntityModel<String> generarFactura(@PathVariable Long ventaId) {
        String factura = ventaService.generarFactura(ventaId);
        return EntityModel.of(factura,
                linkTo(methodOn(VentaControllerV2.class).generarFactura(ventaId)).withSelfRel());
    }
}
