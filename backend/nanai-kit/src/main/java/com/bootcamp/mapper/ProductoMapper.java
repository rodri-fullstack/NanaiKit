package com.bootcamp.mapper;

import com.bootcamp.dto.ProductoRequest;
import com.bootcamp.dto.ProductoResponse;
import com.bootcamp.model.Producto;

public class ProductoMapper {

    public static ProductoResponse toResponse(Producto producto) {
        if (producto == null) return null;
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getTipo(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getActivo(),
                producto.getFechaCreacion()
        );
    }

    public static Producto toEntity(ProductoRequest req) {
        if (req == null) return null;
        Producto p = new Producto();
        p.setNombre(req.nombre());
        p.setTipo(req.tipo());
        p.setDescripcion(req.descripcion());
        p.setStock(req.stock() != null ? req.stock() : 0);
        return p;
    }

    public static void updateEntity(Producto p, ProductoRequest req) {
        if (p == null || req == null) return;
        p.setNombre(req.nombre());
        p.setTipo(req.tipo());
        p.setDescripcion(req.descripcion());
        p.setStock(req.stock() != null ? req.stock() : p.getStock());
    }
}
