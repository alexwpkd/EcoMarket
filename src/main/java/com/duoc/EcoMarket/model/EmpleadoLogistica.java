package com.duoc.EcoMarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "empleado_logistica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoLogistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private int telefono;

    // Un empleado de logística puede gestionar muchos pedidos
    @OneToMany(mappedBy = "empleadoLogistica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
}
