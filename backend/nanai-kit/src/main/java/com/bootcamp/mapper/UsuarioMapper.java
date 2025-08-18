package com.bootcamp.mapper;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.model.Usuario;

public class UsuarioMapper {

    // Convierte de entidad a DTO de respuesta
    public static UsuarioResponse toResponse(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getEdad(),
                usuario.getGenero(),
                usuario.getTipo_usuario(),
                usuario.getActivo(),
                usuario.getFechaRegistro()
        );
    }

    // Convierte de DTO de petición a entidad
    public static Usuario toEntity(UsuarioRequest request) {
        if (request == null) return null;
        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setApellidos(request.apellidos());
        usuario.setEmail(request.email());
        usuario.setPassword(request.password()); // ✅ requerido
        usuario.setTelefono(request.telefono());
        usuario.setDireccion(request.direccion());
        usuario.setEdad(request.edad());
        usuario.setGenero(request.genero());
        usuario.setTipo_usuario(request.tipoUsuario());
        // activo y fecha por defecto, no se incluirían en esta función
        return usuario;
    }

    public static void updateEntity(Usuario usuario, UsuarioRequest request) {
        if (usuario == null || request == null) return;
        usuario.setNombre(request.nombre());
        usuario.setApellidos(request.apellidos());
        if (request.email() != null) usuario.setEmail(request.email());
        if (request.password() != null) usuario.setPassword(request.password());
        usuario.setTelefono(request.telefono());
        usuario.setDireccion(request.direccion());
        usuario.setEdad(request.edad());
        usuario.setGenero(request.genero());
        if (request.tipoUsuario() != null) usuario.setTipo_usuario(request.tipoUsuario());
    }
}

