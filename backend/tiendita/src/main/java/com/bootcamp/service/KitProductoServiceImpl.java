package com.bootcamp.service;

import com.bootcamp.dto.KitProductoRequest;
import com.bootcamp.dto.KitProductoResponse;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.KitProductoMapper;
import com.bootcamp.model.Kit;
import com.bootcamp.model.KitProducto;
import com.bootcamp.model.Producto;
import com.bootcamp.repository.KitProductoRepository;
import com.bootcamp.repository.KitRepository;
import com.bootcamp.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitProductoServiceImpl implements KitProductoService {

    private final KitProductoRepository repository;
    private final KitRepository kitRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional()
    public List<KitProductoResponse> listarTodos() {
        return repository.findAll().stream().map(KitProductoMapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public KitProductoResponse obtenerPorId(Long id) {
        KitProducto kp = repository.findById(id).orElseThrow(() -> new NotFoundException("Kit-Producto no encontrado"));
        return KitProductoMapper.toResponse(kp);
    }

    @Override
    @Transactional
    public KitProductoResponse guardar(KitProductoRequest request) {
        KitProducto kitProducto = KitProductoMapper.toEntity(request);
        if (kitProducto.getKit() != null && kitProducto.getKit().getKitId() != null) {
            Kit kit = kitRepository.findById(kitProducto.getKit().getKitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            kitProducto.setKit(kit);
        }
        if (kitProducto.getProducto() != null && kitProducto.getProducto().getProductoId() != null) {
            Producto p = productoRepository.findById(kitProducto.getProducto().getProductoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            kitProducto.setProducto(p);
        }
        return KitProductoMapper.toResponse(repository.save(kitProducto));
    }

    @Override
    @Transactional
    public KitProductoResponse actualizar(Long id, KitProductoRequest request) {
        KitProducto kp = repository.findById(id).orElseThrow(() -> new NotFoundException("Kit-Producto no encontrado"));
        if (request.kitId() != null) {
            Kit k = kitRepository.findById(request.kitId()).orElseThrow(() -> new NotFoundException("Kit no encontrado"));
            kp.setKit(k);
        }
        if (request.productoId() != null) {
            Producto p = productoRepository.findById(request.productoId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
            kp.setProducto(p);
        }
        return KitProductoMapper.toResponse(repository.save(kp));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Kit-Producto no encontrado");
        repository.deleteById(id);
    }
}