package com.nanai_kit.features.producto.service;

import com.nanai_kit.exception.ApiException;
import com.nanai_kit.exception.BadRequestException;
import com.nanai_kit.exception.NotFoundException;
import com.nanai_kit.features.producto.dto.ProductoRequest;
import com.nanai_kit.features.producto.dto.ProductoResponse;
import com.nanai_kit.features.producto.mapper.ProductoMapper;
import com.nanai_kit.features.producto.model.Producto;
import com.nanai_kit.features.producto.repository.ProductoRepository;
import com.nanai_kit.features.usuario.dto.UsuarioRequestDTO;
import com.nanai_kit.features.usuario.dto.UsuarioResponseDTO;
import com.nanai_kit.features.usuario.mapper.UsuarioMapper;
import com.nanai_kit.features.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    @Override
    public List<ProductoResponse> listarTodas() {
        return repository.findAll().stream().map(ProductoMapper::toResponse).toList();
    }

    public Producto buscarPorId(Long productoId) {
        return repository.findById(productoId).orElseThrow(()
                -> new NotFoundException("Producto no encontrado"));
    }

    public ProductoResponse buscarPorSku(String sku) {
        Producto producto = repository.findBySku(sku).orElseThrow(()
                -> new NotFoundException("Producto no encontrado"));

        return ProductoMapper.toResponse(producto);
    }

    @Override
    public ProductoResponse crearProducto(ProductoRequest request) {

        if (repository.findBySku(request.sku()).isPresent()) {
            throw new ApiException("Ya existe un producto con el mismo SKU");
        }

        Producto producto = new Producto();
        producto.setActivo(request.activo());
        producto.setSku(request.sku());
        producto.setNombre(request.nombre());
        producto.setStock(request.stock());
        producto.setCosto(request.costo());
        return ProductoMapper.toResponse(repository.save(producto));
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = repository.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        ProductoMapper.updateEntity(producto, request);
        return ProductoMapper.toResponse(repository.save(producto));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Producto no encontrado");
        repository.deleteById(id);
    }


}
