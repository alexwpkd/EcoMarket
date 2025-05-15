package com.duoc.EcoMarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String nombre;
    private String correo;
    private String contrasena;
    private int telefono;
    private String direccion;
}
