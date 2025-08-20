package com.nanai_kit.features.producto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "producto", schema = "nanai")
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "sku", length = 60, nullable = false, unique = true)
    private String sku;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "costo", precision = 10, scale = 2, nullable = false)
    private BigDecimal costo = BigDecimal.ZERO;

    @Column(name = "stock", nullable = false)
    @Min(value = 0)
    private Integer stock = 0;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

   /* @ManyToMany(mappedBy = "productos")
    private List<Kit> kits; */
}
