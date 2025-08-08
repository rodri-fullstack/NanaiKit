package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kit_producto")
public class KitProducto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_producto_id")
    private Long kitProductoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kit_id")
    @ToString.Exclude
    private Kit kit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @ToString.Exclude
    private Producto producto;
}