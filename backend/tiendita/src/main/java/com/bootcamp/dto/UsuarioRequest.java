package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record UsuarioRequest(
        String nombre,
        String email,
        String contrasena,
        String telefono,
        String direccion,
        Integer edad,
        String genero,
        String tipoUsuario,
        Boolean activo
) {}