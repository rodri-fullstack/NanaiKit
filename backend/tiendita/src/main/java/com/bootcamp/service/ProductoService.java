package com.bootcamp.service;

<<<<<<< HEAD
import com.bootcamp.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
=======


import com.bootcamp.dto.ProductoRequest;
import com.bootcamp.dto.ProductoResponse;

import java.util.List;

public interface ProductoService {

    List<ProductoResponse> listarTodos();
    ProductoResponse obtenerPorId(Long id);
    ProductoResponse guardar(ProductoRequest productoRequest);
    ProductoResponse actualizar(Long id, ProductoRequest productoRequest);
    void eliminar(Long id);

}
>>>>>>> rodri-fullstack-main
