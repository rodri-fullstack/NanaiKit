package com.bootcamp.service;


import com.bootcamp.dto.TestEmocionalRequest;
import com.bootcamp.dto.TestEmocionalResponse;
import com.bootcamp.exception.ApiException;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.TestEmocionalMapper;
import com.bootcamp.model.TestEmocional;
import com.bootcamp.model.Usuario;
import com.bootcamp.repository.TestEmocionalRepository;
import com.bootcamp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestEmocionalServiceImpl implements TestEmocionalService{

    private final TestEmocionalRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional()
    public List<TestEmocionalResponse> listarTodos() {
        return repository.findAll().stream().map(TestEmocionalMapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public TestEmocionalResponse obtenerPorId(Long id) {
        TestEmocional t = repository.findById(id).orElseThrow(() -> new NotFoundException("Test emocional no encontrado"));
        return TestEmocionalMapper.toResponse(t);
    }

    @Override
    @Transactional
    public TestEmocionalResponse guardar(TestEmocionalRequest request) {
        TestEmocional t = TestEmocionalMapper.toEntity(request);
        if (t.getUsuario() != null && t.getUsuario().getUsuarioId() != null) {
            Usuario u = usuarioRepository.findById(t.getUsuario().getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            t.setUsuario(u);
        }
        return TestEmocionalMapper.toResponse(repository.save(t));
    }

    @Override
    @Transactional
    public TestEmocionalResponse actualizar(Long id, TestEmocionalRequest request) {
        TestEmocional t = repository.findById(id).orElseThrow(() -> new NotFoundException("Test emocional no encontrado"));
        if (request.usuarioId() != null) {
            Usuario u = usuarioRepository.findById(request.usuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            t.setUsuario(u);
        }
        t.setResultado(request.resultado());
        t.setFecha(request.fecha());
        return TestEmocionalMapper.toResponse(repository.save(t));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new ApiException("Test emocional no encontrado");
        repository.deleteById(id);
    }
}