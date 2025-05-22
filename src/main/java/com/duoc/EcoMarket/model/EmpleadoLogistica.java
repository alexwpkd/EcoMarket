package com.duoc.EcoMarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmpleadoLogistica {

    private int id;
    private String nombre;
    private String correo;
    private String contraseña;
}
