package com.bootcamp.service;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.UsuarioMapper;
import com.bootcamp.model.Usuario;
import com.bootcamp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public List<UsuarioResponse> listarTodos() {
        return repository.findAll().stream().map(UsuarioMapper::toResponse).toList();
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario u = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toResponse(u);
    }

    @Override
    @Transactional
    public UsuarioResponse guardar(UsuarioRequest request) {
        Usuario u = UsuarioMapper.toEntity(request);
        return UsuarioMapper.toResponse(repository.save(u));
    }

    @Override
    @Transactional
    public UsuarioResponse actualizar(Long id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setContrasena(request.contrasena());
        usuario.setTelefono(request.telefono());
        usuario.setDireccion(request.direccion());
        usuario.setEdad(request.edad());
        usuario.setGenero(request.genero());
        usuario.setTipoUsuario(request.tipoUsuario());
        if (request.activo() != null) usuario.setActivo(request.activo());
        return UsuarioMapper.toResponse(repository.save(usuario));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Usuario no encontrado");
        repository.deleteById(id);
    }

}
