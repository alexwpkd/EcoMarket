package com.duoc.EcoMarket.services;

import com.duoc.EcoMarket.model.Cliente;
import com.duoc.EcoMarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clRepository;

    public List<Cliente> obtenerTodos() {
        return clRepository.findAll();
    }

    public ClienteService(ClienteRepository clienteRepository) {
        this.clRepository = clienteRepository;
    }

    public Cliente registrarCliente(Cliente cliente) {
        if (clRepository.existsByCorreo(cliente.getCorreo())) {
            return null;
        }
        return clRepository.save(cliente);
    }

    public Cliente iniciarSesion(String correo, String contraseña) {
        Cliente cliente = clRepository.findByCorreo(correo);
        if (cliente == null || !cliente.getContraseña().equals(contraseña)) {
            throw new RuntimeException("Correo o contraseña incorrectos.");
        }
        return cliente;
    }

    public Cliente actualizarPerfil(Long id, Cliente datosActualizados) {
        Cliente cliente = clRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        cliente.setNombre(datosActualizados.getNombre());
        cliente.setDireccion(datosActualizados.getDireccion());
        cliente.setTelefono(datosActualizados.getTelefono());
        cliente.setCorreo(datosActualizados.getCorreo());
        // Puedes manejar la contraseña si decides permitir su cambio

        return clRepository.save(cliente);
    }
}