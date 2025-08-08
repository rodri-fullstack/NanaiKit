package com.bootcamp.service;

import com.bootcamp.dto.ContenidoDigitalRequest;
import com.bootcamp.dto.ContenidoDigitalResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.ContenidoDigitalMapper;
import com.bootcamp.model.ContenidoDigital;
import com.bootcamp.model.Kit;
import com.bootcamp.repository.ContenidoDigitalRepository;
import com.bootcamp.repository.KitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContenidoDigitalServiceImpl implements ContenidoDigitalService {

    private final ContenidoDigitalRepository repository;
    private final KitRepository kitRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContenidoDigitalResponse> listarTodos() {
        return repository.findAll().stream().map(ContenidoDigitalMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ContenidoDigitalResponse obtenerPorId(Long id) {
        ContenidoDigital c = repository.findById(id).orElseThrow(() -> new NotFoundException("Contenido digital no encontrado"));
        return ContenidoDigitalMapper.toResponse(c);
    }

    @Override
    @Transactional
    public ContenidoDigitalResponse guardar(ContenidoDigitalRequest request) {
        ContenidoDigital c = ContenidoDigitalMapper.toEntity(request);
        if (c.getKit() != null && c.getKit().getKitId() != null) {
            Kit k = kitRepository.findById(c.getKit().getKitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            c.setKit(k);
        }
        return ContenidoDigitalMapper.toResponse(repository.save(c));
    }

    @Override
    @Transactional
    public ContenidoDigitalResponse actualizar(Long id, ContenidoDigitalRequest request) {
        ContenidoDigital c = repository.findById(id).orElseThrow(() -> new NotFoundException("Contenido digital no encontrado"));
        if (request.kitId() != null) {
            Kit k = kitRepository.findById(request.kitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            c.setKit(k);
        }
        c.setTipoContenido(request.tipoContenido());
        c.setUrl(request.url());
        return ContenidoDigitalMapper.toResponse(repository.save(c));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Contenido digital no encontrado");
        repository.deleteById(id);
    }
}