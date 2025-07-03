package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.PedidoModelAssembler;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.model.Pedido;
import com.duoc.EcoMarket.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoControllerV2 {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Pedido>> listarPedidos() {
        List<EntityModel<Pedido>> pedidos = pedidoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoControllerV2.class).listarPedidos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> obtenerPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return assembler.toModel(pedido.orElseThrow(() -> new RuntimeException("Pedido no encontrado")));
    }

    @PutMapping("/{id}/estado")
    public EntityModel<Pedido> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Pedido actualizado = pedidoService.actualizarEstado(id, nuevoEstado);
        return assembler.toModel(actualizado);
    }
}
