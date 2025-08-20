package com.nanai_kit.features.usuario.service;

import com.nanai_kit.features.usuario.dto.UsuarioRequestDTO;
import com.nanai_kit.features.usuario.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crear(UsuarioRequestDTO request);
    UsuarioResponseDTO obtenerPorId(Long id);
    List<UsuarioResponseDTO> listar();
    UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request);
    void desactivar(Long id);
    void eliminar(Long id);
}
