package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "kit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kit_id")
    private Long id;

    private String nombre;

    @Column(name = "nivel_ansiedad")
    private String nivelAnsiedad;

    private String descripcion;

    private Double precio;

    private Integer stock = 0;

    private Boolean activo = true;

    @Column(name = "tipo_contenido_digital")
    private String tipoContenidoDigital;

    @Column(name = "url_contenido")
    private String urlContenido;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "kit", cascade = CascadeType.ALL)
    private List<CompraDetalle> compraDetalles;

    @ManyToMany
    @JoinTable(
            name = "kit_producto",
            joinColumns = @JoinColumn(name = "kit_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}
