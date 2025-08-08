package com.bootcamp.service;


import com.bootcamp.dto.PedidoKitRequest;
import com.bootcamp.dto.PedidoKitResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.PedidoKitItemMapper;
import com.bootcamp.model.Kit;
import com.bootcamp.model.Pedido;
import com.bootcamp.model.PedidoKit;
import com.bootcamp.repository.KitRepository;
import com.bootcamp.repository.PedidoKitRepository;
import com.bootcamp.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoKitServiceImpl implements PedidoKitService {

    private final PedidoKitRepository repository;
    private final PedidoRepository pedidoRepository;
    private final KitRepository kitRepository;

    @Override
    @Transactional()
    public List<PedidoKitResponse> listarTodos() {
        return repository.findAll().stream()
                .map(PedidoKitItemMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional()
    public PedidoKitResponse obtenerPorId(Long id) {
        PedidoKit pk = repository.findById(id).orElseThrow(() -> new NotFoundException("PedidoKit no encontrado"));
        return PedidoKitItemMapper.toResponse(pk);
    }

    @Override
    @Transactional
    public PedidoKitResponse guardar(PedidoKitRequest request) {
        PedidoKit pk = new PedidoKit();
        if (request.pedidoId() != null) {
            Pedido p = pedidoRepository.findById(request.pedidoId()).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
            pk.setPedido(p);
        }
        if (request.kitId() != null) {
            Kit k = kitRepository.findById(request.kitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            pk.setKit(k);
        }
        pk.setCantidad(request.cantidad());
        return PedidoKitItemMapper.toResponse(repository.save(pk));
    }

    @Override
    @Transactional
    public PedidoKitResponse actualizar(Long id, PedidoKitRequest request) {
        PedidoKit pk = repository.findById(id).orElseThrow(() -> new NotFoundException("PedidoKit no encontrado"));
        if (request.pedidoId() != null) {
            Pedido p = pedidoRepository.findById(request.pedidoId()).orElseThrow(() -> new NotFoundException("Pedido no encontrado"));
            pk.setPedido(p);
        }
        if (request.kitId() != null) {
            Kit k = kitRepository.findById(request.kitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            pk.setKit(k);
        }
        pk.setCantidad(request.cantidad());
        return PedidoKitItemMapper.toResponse(repository.save(pk));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("PedidoKit no encontrado");
        repository.deleteById(id);
    }
}