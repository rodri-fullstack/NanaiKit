package com.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    private String tipo;
    private String descripcion;

    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    private Boolean activo = true;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToMany(mappedBy = "productos")
    private List<Kit> kits;

}
