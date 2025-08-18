package com.bootcamp.service;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.security.dto.RegisterRequest;

import java.util.List;

public interface UsuarioService {
    boolean registrarUsuario(RegisterRequest request);

    List<UsuarioResponse> listarTodos();
    UsuarioResponse obtenerPorId(Long id);
    UsuarioResponse guardar(UsuarioRequest cliente);
    UsuarioResponse actualizar(Long id, UsuarioRequest cliente);
    void eliminar(Long id);
}
