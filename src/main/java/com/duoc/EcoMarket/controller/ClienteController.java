package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService ClienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return ClienteService.getClientes();
    }

    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        return ClienteService.guardarCliente(cliente);
    }

    @GetMapping({"{id}"})
    public Cliente buscarCliente(@PathVariable int id){
        return ClienteService.getClienteId(id);
    }

    @DeleteMapping({"{id}"})
    public String eliminarLibro(@PathVariable int id){
        return ClienteService.deleteCliente(id);
    }
}
