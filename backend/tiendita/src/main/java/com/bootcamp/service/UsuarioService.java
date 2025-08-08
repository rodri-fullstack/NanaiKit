package com.bootcamp.service;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listarTodos();
    UsuarioResponse obtenerPorId(Long id);
    UsuarioResponse guardar(UsuarioRequest usuarioRequest);
    UsuarioResponse actualizar(Long id, UsuarioRequest usuarioRequest);
    void eliminar(Long id);
}
