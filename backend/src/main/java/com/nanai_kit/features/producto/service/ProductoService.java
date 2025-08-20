package com.nanai_kit.features.producto.service;

import com.nanai_kit.features.producto.dto.ProductoRequest;
import com.nanai_kit.features.producto.dto.ProductoResponse;
import com.nanai_kit.features.producto.model.Producto;

import java.util.List;

public interface ProductoService {
    List<ProductoResponse> listarTodas();
    ProductoResponse crearProducto(ProductoRequest request);
    Producto buscarPorId(Long productoId);
    ProductoResponse buscarPorSku(String sku);
    ProductoResponse actualizar(Long id, ProductoRequest request);
    void eliminar(Long id);
}
