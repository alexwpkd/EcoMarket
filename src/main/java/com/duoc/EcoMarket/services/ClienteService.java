package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        return clienteRepository.obtenerClientes();
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.guardarCliente(cliente);
    }

    public Cliente getClienteId(int id){
        return clienteRepository.buscarPoriD(id);
    }

    public String deleteCliente(int id){
        clienteRepository.eliminarCliente(id);
        return "Cliente eliminado";
    }

}
