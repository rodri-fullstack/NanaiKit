package com.bootcamp.dto;

public record ClienteRequest(
        String nombre,
        String email,
        String contrasena,
        String direccion,
        String telefono,
        Integer edad,
        String genero,
        String tipo_usuario,
        Boolean activo
) {}

//  No queremos que el cliente nos mande el id, y mucho menos nos devuelva password en la respuesta.