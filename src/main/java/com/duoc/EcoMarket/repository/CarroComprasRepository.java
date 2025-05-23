package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.CarroCompras;
import com.duoc.EcoMarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroComprasRepository extends JpaRepository<CarroCompras, Long> {

    Optional<CarroCompras> findByCliente(Cliente cliente);
}
