package com.bootcamp.service;

import com.bootcamp.dto.PedidoRequest;
import com.bootcamp.dto.PedidoResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.PedidoKitItemMapper;
import com.bootcamp.mapper.PedidoMapper;
import com.bootcamp.model.Pedido;
import com.bootcamp.model.PedidoKit;
import com.bootcamp.model.Usuario;
import com.bootcamp.repository.PedidoRepository;
import com.bootcamp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

    private final PedidoRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional()
    public List<PedidoResponse> listarTodos() {
        return repository.findAll().stream().map(PedidoMapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public PedidoResponse obtenerPorId(Long id) {
        Pedido p = repository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
        return PedidoMapper.toResponse(p);
    }

    @Override
    @Transactional
    public PedidoResponse guardar(PedidoRequest request) {
        Pedido p = PedidoMapper.toEntity(request);

        if (p.getUsuario() != null && p.getUsuario().getUsuarioId() != null) {
            Usuario u = usuarioRepository.findById(p.getUsuario().getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            p.setUsuario(u);
        }
        if (p.getFechaCreacion() == null) p.setFechaCreacion(LocalDateTime.now());

        if (p.getItems() != null) {
            for (PedidoKit pk : p.getItems()) {
                pk.setPedido(p);
            }
        }
        return PedidoMapper.toResponse(repository.save(p));
    }

    @Override
    @Transactional
    public PedidoResponse actualizar(Long id, PedidoRequest request) {
        Pedido existing = repository.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        if (request.usuarioId() != null) {
            Usuario u = usuarioRepository.findById(request.usuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            existing.setUsuario(u);
        }
        if (request.fechaCreacion() != null) existing.setFechaCreacion(request.fechaCreacion());
        existing.setTotal(request.total());

        List<PedidoKit> nuevos = new ArrayList<>();
        if (request.items() != null) {
            for (var it : request.items()) {
                PedidoKit pk = PedidoKitItemMapper.toEntity(it);
                pk.setPedido(existing);
                nuevos.add(pk);
            }
        }
        existing.getItems().clear();
        existing.getItems().addAll(nuevos);

        return PedidoMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Pedido no encontrado");
        repository.deleteById(id);
    }
}