package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "kit")
public class Kit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_id")
    private Long kitId;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "nivel_ansiedad")
    private String nivelAnsiedad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock")
    private Integer stock = 0;

    @Column(name = "activo")
    private Boolean activo = true;
}