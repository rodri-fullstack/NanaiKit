package com.bootcamp.dto;

public record UsuarioRequest (
        String nombre,
        String apellidos,
        String email,
        String password,
        String telefono,
        String direccion,
        Integer edad,
        String genero,
        String tipoUsuario
) {}