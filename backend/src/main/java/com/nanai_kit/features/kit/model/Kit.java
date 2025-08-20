package com.nanai_kit.features.kit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kit", schema = "nanai")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kit")
    private Long id;

    @Column(name = "codigo", length = 60, nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelAnsiedadKit nivel;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "descripcion_breve", columnDefinition = "TEXT")
    private String descripcionBreve;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "kit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KitProducto> kitProductos = new ArrayList<>();

    // Helper methods para mantener la relación bidireccional
    public void addKitProducto(KitProducto kp) {
        kp.setKit(this);
        this.kitProductos.add(kp);
    }

    public void removeKitProducto(KitProducto kp) {
        this.kitProductos.remove(kp);
        kp.setKit(null);
    }
}
