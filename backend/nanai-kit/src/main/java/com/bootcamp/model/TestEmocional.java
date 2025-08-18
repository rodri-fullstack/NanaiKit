package com.bootcamp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_emocional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEmocional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String resultado;

    private LocalDateTime fecha = LocalDateTime.now();
}
