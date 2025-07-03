package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida")
    })
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<Cliente> clientes = clService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Registrar un nuevo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "El correo ya está registrado")
    })
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clService.registrarCliente(cliente);
        if (nuevoCliente == null) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }
        return ResponseEntity.ok(nuevoCliente);
    }

    @Operation(summary = "Iniciar sesión del cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Correo o contraseña incorrectos")
    })
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Cliente datosLogin) {
        Cliente cliente = clService.iniciarSesion(datosLogin.getCorreo(), datosLogin.getContraseña());
        if (cliente == null) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos.");
        }
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Actualizar datos del cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "400", description = "Cliente no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody Cliente datosActualizados) {
        Cliente clienteActualizado = clService.actualizarPerfil(id, datosActualizados);
        if (clienteActualizado == null) {
            return ResponseEntity.badRequest().body("Cliente no encontrado.");
        }
        return ResponseEntity.ok(clienteActualizado);
    }
}
