package com.duoc.EcoMarket.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int idPedido;
    private String correoCliente;
    private List<Producto> productos;
    private String estado;
}
