package com.duoc.EcoMarket.repository;

import com.duoc.EcoMarket.model.Venta;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VentaRepository {

    private List<Venta> ventas = new ArrayList<>();

    public Venta registrar(Venta v) {
        ventas.add(v);
        return v;
    }

    public List<Venta> listar() {
        return ventas;
    }
}
