package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentasRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClienteNombre(String nombre);

}
