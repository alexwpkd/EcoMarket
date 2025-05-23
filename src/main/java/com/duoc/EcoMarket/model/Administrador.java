package com.duoc.EcoMarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "administrador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String contraseña;
    private int telefono;

    // Un administrador puede gestionar muchos empleados de ventas
    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
    private List<EmpleadoVentas> empleadosVentas;

    // Un administrador puede gestionar muchos empleados de logística
    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
    private List<EmpleadoLogistica> empleadosLogistica;
}
