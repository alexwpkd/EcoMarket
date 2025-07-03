package com.duoc.EcoMarket.controllerv2;

import com.duoc.EcoMarket.assemblers.ClienteModelAssembler;
import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Cliente>> obtenerTodos() {
        List<EntityModel<Cliente>> clientes = clienteService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
                linkTo(methodOn(ClienteControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerTodos().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return assembler.toModel(cliente);
    }

    @PutMapping("/actualizar/{id}")
    public EntityModel<Cliente> actualizarPerfil(@PathVariable Long id, @RequestBody Cliente datos) {
        Cliente actualizado = clienteService.actualizarPerfil(id, datos);
        return assembler.toModel(actualizado);
    }
}
