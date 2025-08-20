package com.nanai_kit.features.usuario.mapper;

import com.nanai_kit.features.usuario.dto.UsuarioRequestDTO;
import com.nanai_kit.features.usuario.dto.UsuarioResponseDTO;
import com.nanai_kit.features.usuario.model.RolUsuario;
import com.nanai_kit.features.usuario.model.Usuario;

public class UsuarioMapper {

    public static Usuario toNewEntity(UsuarioRequestDTO requestDTO, String hash) {
        return Usuario.builder()
                .nombre(requestDTO.nombre())
                .email(requestDTO.email())
                .contrasenaHash(hash)
                .rol(requestDTO.rol() != null ? requestDTO.rol() : RolUsuario.USUARIO)
                .telefono(requestDTO.telefono())
                .comuna(requestDTO.comuna())
                .direccion(requestDTO.direccion())
                .activo(requestDTO.activo() == null ? true : requestDTO.activo())
                .build();
    }

    public static void applyUpdates(Usuario usuario, UsuarioRequestDTO requestDTO, String newHashOrNull) {
        if (requestDTO.nombre() != null) usuario.setNombre(requestDTO.nombre());
        if (requestDTO.email() != null) usuario.setEmail(requestDTO.email());
        if (newHashOrNull != null) usuario.setContrasenaHash(newHashOrNull);
        if (requestDTO.rol() != null) usuario.setRol(requestDTO.rol());
        if (requestDTO.telefono() != null) usuario.setTelefono(requestDTO.telefono());
        if (requestDTO.comuna() != null) usuario.setComuna(requestDTO.comuna());
        if (requestDTO.direccion() != null) usuario.setDireccion(requestDTO.direccion());
        if (requestDTO.activo() != null) usuario.setActivo(requestDTO.activo());
    }

    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getTelefono(),
                usuario.getComuna(),
                usuario.getDireccion(),
                usuario.isActivo()
        );
    }
}
