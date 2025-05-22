package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositoryt extends JpaRepository<Cliente, Long>{

    // Crear Cuenta: Verifica si ya existe el correo antes de registrar
    boolean existsByCorreo(String correo);

    // Iniciar Sesión: Buscar cliente por correo
    Cliente findByCorreo(String correo);

}