package com.bootcamp.service;

import com.bootcamp.dto.KitRequest;
import com.bootcamp.dto.KitResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.KitMapper;
import com.bootcamp.model.Kit;
import com.bootcamp.repository.KitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitServiceImpl implements KitService{

    private final KitRepository repository;

    @Override
    @Transactional()
    public List<KitResponse> listarTodos() {
        return repository.findAll().stream().map(KitMapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public KitResponse obtenerPorId(Long id) {
        Kit k = repository.findById(id).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
        return KitMapper.toResponse(k);
    }

    @Override
    @Transactional
    public KitResponse guardar(KitRequest request) {
        Kit k = KitMapper.toEntity(request);
        return KitMapper.toResponse(repository.save(k));
    }

    @Override
    @Transactional
    public KitResponse actualizar(Long id, KitRequest request) {
        Kit k = repository.findById(id).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
        k.setNombre(request.nombre());
        k.setNivelAnsiedad(request.nivelAnsiedad());
        k.setDescripcion(request.descripcion());
        if (request.precio() != null) k.setPrecio(request.precio());
        if (request.stock() != null) k.setStock(request.stock());
        if (request.activo() != null) k.setActivo(request.activo());
        return KitMapper.toResponse(repository.save(k));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Kit no encontrado");
        repository.deleteById(id);
    }
}