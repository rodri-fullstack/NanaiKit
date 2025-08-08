package com.example.demo.service;

import com.bootcamp.dto.ProductoRequest;
import com.bootcamp.dto.ProductoResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.ProductoMapper;
import com.bootcamp.model.Producto;
import com.bootcamp.repository.ProductoRepository;
import com.bootcamp.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    @Transactional()
    public List<ProductoResponse> listarTodos() {
        return repository.findAll().stream().map(ProductoMapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public ProductoResponse obtenerPorId(Long id) {
        Producto p = repository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        return ProductoMapper.toResponse(p);
    }

    @Override
    @Transactional
    public ProductoResponse guardar(ProductoRequest request) {
        Producto p = ProductoMapper.toEntity(request);
        if (p.getFechaCreacion() == null) p.setFechaCreacion(LocalDateTime.now());
        return ProductoMapper.toResponse(repository.save(p));
    }

    @Override
    @Transactional
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto p = repository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        p.setNombre(request.nombre());
        p.setTipo(request.tipo());
        p.setDescripcion(request.descripcion());
        if (request.stock() != null) p.setStock(request.stock());
        if (request.activo() != null) p.setActivo(request.activo());
        if (request.fechaCreacion() != null) p.setFechaCreacion(request.fechaCreacion());
        return ProductoMapper.toResponse(repository.save(p));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Producto no encontrado");
        repository.deleteById(id);
    }
}