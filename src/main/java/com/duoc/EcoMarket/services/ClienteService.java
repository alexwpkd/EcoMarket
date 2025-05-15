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

    public Cliente getClienteCorreo(String correo){
        return clienteRepository.buscarPorCorreo(correo);
    }

    public String deleteCliente(String correo){
        return clienteRepository.eliminarCliente(correo);
    }

    public Cliente updateCliente(Cliente cliente){
        return clienteRepository.actualizarCliente(cliente);
    }

    public Cliente inicioSesion(String correo, String contrasena){
        return clienteRepository.iniciarSesion(correo, contrasena);
    }
}
