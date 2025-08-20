package com.nanai_kit.features.kit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nanai_kit.features.producto.model.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kit_producto", schema = "nanai")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KitProducto {

    @EmbeddedId
    private KitProductoId id;

    @ManyToOne
    @MapsId("idKit")
    @JsonBackReference
    @JoinColumn(name = "id_kit")
    private Kit kit;

    @ManyToOne
    @MapsId("idProducto")
    @JsonBackReference
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;
}
