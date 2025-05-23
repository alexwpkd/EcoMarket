package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.EmpleadoLogistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoLogisticaRepository extends JpaRepository<EmpleadoLogistica, Long> {

    EmpleadoLogistica findByRut(String rut);
}
