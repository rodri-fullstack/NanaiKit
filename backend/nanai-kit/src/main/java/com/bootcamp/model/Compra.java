package com.bootcamp.model;

import com.bootcamp.enums.EstadoCompra;
import com.bootcamp.enums.FormaPago;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compra_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra = LocalDateTime.now();

    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "formaPago")
    private FormaPago formaPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoCompra")
    private EstadoCompra estadoCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<CompraDetalle> detalles;
}
