package com.duoc.EcoMarket.controller;

import com.duoc.EcoMarket.model.Administrador;
import com.duoc.EcoMarket.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService AdminService;

    @GetMapping
    public List<Administrador> listarAdministradores(){
        return AdminService.getAdministradors();
    }

    @PostMapping
    public Administrador guardarAdministradores(@RequestBody Administrador ad){
        return AdminService.guardarAdmin(ad);
    }

    @GetMapping({"{correo}"})
    public Administrador buscarAdministrador(@PathVariable String correo){
        return AdminService.getAdminCorreo(correo);
    }

    @DeleteMapping({"{correo}"})
    public String eliminarAdministradores(@PathVariable String correo){
        return AdminService.deleteAdmin(correo);
    }

    @PutMapping({"{correo}"})
    public Administrador actualizarAdministrador(@PathVariable String correo, @RequestBody Cliente cliente){
        return ClienteService.updateCliente((cliente));
    }

    @PostMapping("/login")
    public Cliente iniciarSesion(@RequestBody Cliente cliente){
        return ClienteService.inicioSesion(cliente.getCorreo(), cliente.getContrasena());
    }
}
