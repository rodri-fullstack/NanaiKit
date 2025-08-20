package com.nanai_kit.features.producto.mapper;


import com.nanai_kit.features.producto.dto.ProductoRequest;
import com.nanai_kit.features.producto.dto.ProductoResponse;
import com.nanai_kit.features.producto.model.Producto;

public class ProductoMapper {

    public static ProductoResponse toResponse(Producto producto) {
        if (producto == null) return null;
        return new ProductoResponse(
                producto.getId(),
                producto.getSku(),
                producto.getNombre(),
                producto.getCosto(),
                producto.getStock(),
                producto.getActivo()
        );
    }

    public static Producto toEntity(ProductoRequest request) {
        if (request == null) return null;
        Producto producto = new Producto();
        producto.setSku(request.sku());
        producto.setNombre(request.nombre());
        producto.setCosto(request.costo());
        producto.setStock(request.stock() != null ? request.stock() : 0);
        producto.setActivo(request.activo());
        return producto;
    }

    public static void updateEntity(Producto producto, ProductoRequest request) {
        if (producto == null || request == null) return;
        if (request.sku() != null) producto.setSku(request.sku());
        if (request.nombre() != null) producto.setNombre(request.nombre());
        if (request.costo() != null) producto.setCosto(request.costo());
        if (request.stock() != null) producto.setStock(request.stock());
        if (request.activo() != null) producto.setActivo(request.activo());
    }

}
