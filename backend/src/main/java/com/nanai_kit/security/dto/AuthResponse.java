package com.nanai_kit.security.dto;

import com.nanai_kit.features.usuario.model.RolUsuario;

public record AuthResponse(
        String nombre,
        String email,
        RolUsuario rol,
        String token
) {}
