package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    // Verifica si ya existe el correo antes de registrar
    boolean existsByCorreo(String correo);

    // Buscar cliente por correo
    Cliente findByCorreo(String correo);

}