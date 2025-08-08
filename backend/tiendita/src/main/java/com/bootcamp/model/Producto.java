package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private Integer stock = 0;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

}