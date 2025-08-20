package com.nanai_kit.features.usuario.dto;

import com.nanai_kit.features.usuario.model.RolUsuario;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String email,
        RolUsuario rol,
        String telefono,
        String comuna,
        String direccion,
        boolean activo
) { }
