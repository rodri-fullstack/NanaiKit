package com.bootcamp.mapper;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioResponse.builder()
                .usuarioId(usuario.getUsuarioId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .direccion(usuario.getDireccion())
                .edad(usuario.getEdad())
                .genero(usuario.getGenero())
                .tipoUsuario(usuario.getTipoUsuario())
                .activo(usuario.getActivo())
                .build();
    }

    public static Usuario toEntity(UsuarioRequest request) {
        if (request == null) return null;
        Usuario u = new Usuario();
        u.setNombre(request.nombre());
        u.setEmail(request.email());
        u.setContrasena(request.contrasena());
        u.setTelefono(request.telefono());
        u.setDireccion(request.direccion());
        u.setEdad(request.edad());
        u.setGenero(request.genero());
        u.setTipoUsuario(request.tipoUsuario());
        u.setActivo(request.activo() != null ? request.activo() : true);
        return u;
    }
}
