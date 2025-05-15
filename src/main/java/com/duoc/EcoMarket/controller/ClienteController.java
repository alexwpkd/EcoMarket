package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
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


    @GetMapping({"{correo}"})
    public Cliente buscarCliente(@PathVariable String correo){
        return ClienteService.getClienteCorreo(correo);
    }

    @DeleteMapping({"{correo}"})
    public String eliminarCliente(@PathVariable String correo){
        return ClienteService.deleteCliente(correo);
    }

    @PutMapping({"{correo}"})
    public Cliente actualizarCliente(@PathVariable String correo, @RequestBody Cliente cliente){
        return ClienteService.updateCliente((cliente));
    }

    @PostMapping("/login")
    public Cliente iniciarSesion(@RequestBody Cliente cliente){
        return ClienteService.inicioSesion(cliente.getCorreo(), cliente.getContrasena());
    }
}
