package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Administrador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class AdministradorRepository {

    //Lista guardar Administradores
    private List<Administrador> listaAdmin = new ArrayList<>();

    public List<Administrador> obtenerAdministrador(){
        return listaAdmin;
    }

    public Administrador iniciarSesion(String correo, String contrasena) {
        Administrador admin = buscarPorCorreo(correo);
        if (admin != null && admin.getContraseña().equals(contrasena)) {
            return admin;
        }
        return null;
    }

    public Administrador guardarAdmin(Administrador ad){
        listaAdmin.add(ad);
        return ad;
    }

    public Administrador actualizarAdmin(Administrador ad){
        for(int i = 0; i < listaAdmin.size(); i++){
            if(listaAdmin.get(i).getCorreo().equals(ad.getCorreo() )){
                listaAdmin.set(i,ad);
                return ad;
            }
        }
        return null;
    }

    public Administrador buscarPorCorreo(String correo){
        for (Administrador tmp : listaAdmin){
            if (tmp.getCorreo() == correo){
                return tmp;
            }
        }
        return null;
    }

    public String eliminarAdmin(String correo) {
        Administrador admin = buscarPorCorreo(correo);
        if (admin != null) {
            listaAdmin.remove(admin);
            return "El Administrador ha sido eliminado \n" + listaAdmin;
        }
        return "No se ha encontrado el administrador";
    }

}

