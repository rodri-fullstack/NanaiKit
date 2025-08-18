package com.bootcamp.security.dto;

import com.bootcamp.enums.NombreRol;

import java.util.List;

public record RegisterRequest(
        String nombre,
        String apellidos,
        String email,
        String password,
        String telefono,
        String direccion,
        Integer edad,
        String genero,
        String tipoUsuario,
        List<NombreRol> roles
) {}
