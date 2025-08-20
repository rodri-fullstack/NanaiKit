package com.nanai_kit.features.usuario.dto;

import com.nanai_kit.features.usuario.model.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @Size(max = 100) String nombre,
        @Email @Size(max = 255) String email,
        @Size(min = 6, max = 100) String contrasena,      // crear: requerida; actualizar: opcional
        RolUsuario rol,                                   // si es null, se mantiene/usa USUARIO en crear
        @Size(max = 20)
        @Pattern(regexp = "^\\+?[0-9\\s-]{7,20}$", message = "Teléfono inválido")
        String telefono,
        @Size(max = 100) String comuna,
        @Size(max = 255) String direccion,
        Boolean activo                                     // actualizar: si viene, setea; si null, no cambia
) { }
