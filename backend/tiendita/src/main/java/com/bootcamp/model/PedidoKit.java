package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido_kit")
public class PedidoKit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_kit_id")
    private Long pedidoKitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @ToString.Exclude
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kit_id")
    @ToString.Exclude
    private Kit kit;

    @Column(name = "cantidad")
    private Integer cantidad;
}