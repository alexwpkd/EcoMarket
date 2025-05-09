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
    public Cliente buscarPoriD(int id){
        for (Cliente tmp : listaClientes){
            if (tmp.getId() == id){
                return tmp;
            }
        }
        return null;
    }

    public Cliente guardarCliente(Cliente cl){
        listaClientes.add(cl);
        return cl;
    }

    public String eliminarCliente(int id) {
        Cliente cliente = buscarPoriD(id);
        if (cliente != null) {
            listaClientes.remove(cliente);
            return "El cliente ha sido borrado \n" + listaClientes;
        }
        return "No se ha encontrado el cliente";
    }
























}
