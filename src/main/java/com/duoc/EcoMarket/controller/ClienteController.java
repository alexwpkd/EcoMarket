package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<Cliente> clientes = clService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }


    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clService.registrarCliente(cliente);
        if (nuevoCliente == null) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }
        return ResponseEntity.ok(nuevoCliente);
    }


    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Cliente datosLogin) {
        Cliente cliente = clService.iniciarSesion(datosLogin.getCorreo(), datosLogin.getContraseña());
        if (cliente == null) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos.");
        }
        return ResponseEntity.ok(cliente);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody Cliente datosActualizados) {
        Cliente clienteActualizado = clService.actualizarPerfil(id, datosActualizados);
        if (clienteActualizado == null) {
            return ResponseEntity.badRequest().body("Cliente no encontrado.");
        }
        return ResponseEntity.ok(clienteActualizado);
    }
}
