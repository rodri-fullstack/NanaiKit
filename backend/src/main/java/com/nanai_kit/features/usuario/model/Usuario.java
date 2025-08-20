package com.nanai_kit.features.usuario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "nanai")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @JsonBackReference
    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false /*, columnDefinition = "rol_usuario_enum"*/)
    private RolUsuario rol = RolUsuario.USUARIO;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    // nuevos campos
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "comuna", length = 100)
    private String comuna;

    @Column(name = "direccion", length = 255)
    private String direccion;
}
