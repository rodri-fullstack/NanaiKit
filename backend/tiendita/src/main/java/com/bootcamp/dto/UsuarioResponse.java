package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record UsuarioResponse(
        Long usuarioId,
        String nombre,
        String email,
        String telefono,
        String direccion,
        Integer edad,
        String genero,
        String tipoUsuario,
        Boolean activo
) {}