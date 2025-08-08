package com.bootcamp.service;

<<<<<<< HEAD
import com.bootcamp.model.Producto;
import com.bootcamp.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
=======
import com.bootcamp.dto.ProductoRequest;
import com.bootcamp.dto.ProductoResponse;
import com.bootcamp.mapper.ProductoMapper;
import com.bootcamp.model.Producto;
import com.bootcamp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bootcamp.mapper.ProductoMapper.toResponse;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository repository;

    @Override
    public ProductoResponse guardar(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.nombre());
       // producto.setPrecio(request.precio());
        producto.setStock(request.stock());
        return toResponse(repository.save(producto));
    }

    @Override
    public ProductoResponse obtenerPorId(Long id) {
        return repository.findById(id)
                .map(ProductoMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<ProductoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(ProductoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(request.nombre());
     //   producto.setPrecio(request.precio());
        producto.setStock(request.stock());

        return toResponse(repository.save(producto));
>>>>>>> rodri-fullstack-main
    }

    @Override
    public void eliminar(Long id) {
<<<<<<< HEAD
        productoRepository.deleteById(id);
    }
}
=======
        repository.deleteById(id);
    }

}
>>>>>>> rodri-fullstack-main
