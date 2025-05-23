package com.duoc.EcoMarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "empleado_ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correo;

    private int telefono;

    // Relación uno a muchos con Venta
    @OneToMany(mappedBy = "empleadoVentas")
    private List<Venta> ventas;

    @ManyToOne
    @JoinColumn(name = "administrador_id")
    private Administrador administrador;

}
