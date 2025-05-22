package com.duoc.EcoMarket.repository;
import com.duoc.EcoMarket.model.CarroCompras;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarroComprasRepository {
    private List<CarroCompras> carritos = new ArrayList<>();

    public CarroCompras obtenerCarrito(String correoCliente) {
        for (CarroCompras c : carritos) {
            if (c.getCorreoCliente().equals(correoCliente)) {
                return c;
            }
        }
        CarroCompras nuevo = new CarroCompras();
        nuevo.setCorreoCliente(correoCliente);
        carritos.add(nuevo);
        return nuevo;
    }

    public String actualizarCarrito(CarroCompras carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCorreoCliente().equals(carrito.getCorreoCliente())) {
                carritos.set(i, carrito);
                return "Carro de compras actualizado";
            }
        }
        carritos.add(carrito);
        return "Se ha añadido correctamente";
    }
}