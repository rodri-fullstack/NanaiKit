package com.nanai_kit.security.dto;

import com.nanai_kit.features.usuario.model.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(max = 100) String nombre,
        @NotBlank @Email @Size(max = 255) String email,
        @NotBlank @Size(min = 6, max = 100) String contrasena,
        RolUsuario rol,          // opcional, default USUARIO
        String telefono,
        String comuna,
        String direccion
) {}
