package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compra_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_detalle_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "kit_id")
    private Kit kit;

    private Integer cantidad = 1;

    @Column(name = "precio_unitario")
    private Double precioUnitario;
}
