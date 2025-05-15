package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Administrador;
import com.duoc.EcoMarket.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository adminRepository;

    public List<Administrador> getAdministradors(){
        return adminRepository.obtenerAdministrador();
    }

    public Administrador guardarAdmin(Administrador ad){
        return adminRepository.guardarAdmin(ad);
    }

    public Administrador getAdminCorreo(String correo){
        return adminRepository.buscarPorCorreo(correo);
    }

    public String deleteAdmin(String correo){
        return adminRepository.eliminarAdmin(correo);
    }

    public Administrador updateAdmin(Administrador ad){
        return adminRepository.actualizarAdmin(ad);
    }

    public Administrador inicioSesion(String correo, String contrasena){
        return adminRepository.iniciarSesion(correo, contrasena);
    }

}
