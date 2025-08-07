package com.bootcamp.mapper;

import com.bootcamp.dto.ClienteRequest;
import com.bootcamp.dto.ClienteResponse;
import com.bootcamp.model.Cliente;

public class ClienteMapper {

    // Convierte de entidad a DTO de respuesta
    public static ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .usuario_id(cliente.getUsuario_id())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .build();
    }

    // Convierte de DTO de petición a entidad
    public static Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());
        cliente.setContrasena(request.contrasena()); // ✅ requerido
        cliente.setDireccion(request.direccion());   // ✅ requerido
        cliente.setTelefono(request.telefono());
        cliente.setEdad(request.edad());
        cliente.setGenero(request.genero());
        cliente.setTipo_usuario(request.tipo_usuario());
        cliente.setActivo(request.activo() != null ? request.activo() : true); // por defecto true
        return cliente;
    }

}

