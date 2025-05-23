package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.EmpleadoVentas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoVentasRepository extends JpaRepository<EmpleadoVentas, Long> {

    EmpleadoVentas findByCorreo(String correo);

}
