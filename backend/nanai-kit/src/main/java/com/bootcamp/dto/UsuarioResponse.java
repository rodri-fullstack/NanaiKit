package com.bootcamp.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder

public record UsuarioResponse (
    Long id,
    String nombre,
    String apellidos,
    String email,
    String direccion,
    String telefono,
    Integer edad,
    String genero,
    String tipoUsuario,
    Boolean activo,
    LocalDateTime fechaRegistro
) {}

