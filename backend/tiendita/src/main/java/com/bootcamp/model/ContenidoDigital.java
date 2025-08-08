package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contenido_digital")
public class ContenidoDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contenido_id")
    private Long contenidoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kit_id")
    @ToString.Exclude
    private Kit kit;

    @Column(name = "tipo_contenido", length = 50)
    private String tipoContenido;

    @Column(name = "url", length = 255)
    private String url;
}