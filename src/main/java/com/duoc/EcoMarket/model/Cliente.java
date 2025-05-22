package com.duoc.EcoMarket.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(unique= true, nullable=false)
    private String correo;

    @Column( nullable=false)
    private String contraseña;

    @Column(nullable=false)
    private int telefono;

    @Column(nullable=false)
    private String direccion;
}
