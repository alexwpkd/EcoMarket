package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.ProductoModelAssembler;
import com.duoc.EcoMarket.model.Producto;
import com.duoc.EcoMarket.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Producto>> listarProductos() {
        List<EntityModel<Producto>> productos = productoService.listarProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).listarProductos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        return assembler.toModel(producto.orElseThrow(() -> new RuntimeException("Producto no encontrado")));
    }

    @GetMapping("/categoria/{categoria}")
    public CollectionModel<EntityModel<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        List<EntityModel<Producto>> productos = productoService.buscarPorCategoria(categoria).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControllerV2.class).buscarPorCategoria(categoria)).withSelfRel());
    }
}
