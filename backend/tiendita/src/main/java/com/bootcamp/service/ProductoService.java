package com.bootcamp.service;



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