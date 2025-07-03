package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
@Tag(name = "Clientes", description = "Gestión de clientes y autenticación")
public class ClienteController {

    @Autowired
    private ClienteService clService;

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<Cliente> clientes = clService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Registrar un nuevo cliente")
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clService.registrarCliente(cliente);
        if (nuevoCliente == null) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }
        return ResponseEntity.ok(nuevoCliente);
    }

    @Operation(summary = "Iniciar sesión del cliente")
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Cliente datosLogin) {
        Cliente cliente = clService.iniciarSesion(datosLogin.getCorreo(), datosLogin.getContraseña());
        if (cliente == null) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos.");
        }
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Actualizar datos del cliente")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody Cliente datosActualizados) {
        Cliente clienteActualizado = clService.actualizarPerfil(id, datosActualizados);
        if (clienteActualizado == null) {
            return ResponseEntity.badRequest().body("Cliente no encontrado.");
        }
        return ResponseEntity.ok(clienteActualizado);
    }
}
