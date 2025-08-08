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
        cliente.setNombre(request.getNombre());
        cliente.setEmail(request.getEmail());
        cliente.setContrasena(request.getContrasena()); // ✅ requerido
        cliente.setDireccion(request.getDireccion());   // ✅ requerido
        cliente.setTelefono(request.getTelefono());
        cliente.setEdad(request.getEdad());
        cliente.setGenero(request.getGenero());
        cliente.setTipo_usuario(request.getTipo_usuario());
        cliente.setActivo(true);
        return cliente;
    }

}

