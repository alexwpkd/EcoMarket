package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ClienteRepository {

    //Lista guardar clientes
    private List<Cliente> listaClientes = new ArrayList<>();

    public List<Cliente> obtenerClientes(){
        return listaClientes;
    }

    public Cliente buscarPorCorreo(String correo){
        for (Cliente tmp : listaClientes){
            if (tmp.getCorreo() == correo){
                return tmp;
            }
        }
        return null;
    }

    public Cliente iniciarSesion(String correo, String contrasena) {
        Cliente cliente = buscarPorCorreo(correo);
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            return cliente;
        }
        return null;
    }

    public Cliente guardarCliente(Cliente cl){
        listaClientes.add(cl);
        return cl;
    }

    public Cliente actualizarCliente(Cliente cl){

        for(int i = 0; i < listaClientes.size(); i++){
            if(listaClientes.get(i).getCorreo().equals(cl.getCorreo() )){
                listaClientes.set(i,cl);
                return cl;
            }
        }
        return null;
    }

    public String eliminarCliente(String correo) {
        Cliente cliente = buscarPorCorreo(correo);
        if (cliente != null) {
            listaClientes.remove(cliente);
            return "El cliente ha sido borrado \n" + listaClientes;
        }
        return "No se ha encontrado el cliente";
    }

}
