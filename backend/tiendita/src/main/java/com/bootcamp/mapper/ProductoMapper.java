package com.bootcamp.mapper;


import com.bootcamp.dto.ProductoRequest;
import com.bootcamp.dto.ProductoResponse;
import com.bootcamp.model.Producto;

import java.time.LocalDateTime;

public class ProductoMapper {

    public static ProductoResponse toResponse(Producto p) {
        if (p == null) return null;
        return ProductoResponse.builder()
                .productoId(p.getProductoId())
                .nombre(p.getNombre())
                .tipo(p.getTipo())
                .descripcion(p.getDescripcion())
                .stock(p.getStock())
                .activo(p.getActivo())
                .fechaCreacion(p.getFechaCreacion())
                .build();
    }

    public static Producto toEntity(ProductoRequest request) {
        if (request == null) return null;
        Producto p = new Producto();
        p.setNombre(request.nombre());
        p.setTipo(request.tipo());
        p.setDescripcion(request.descripcion());
        p.setStock(request.stock() != null ? request.stock() : 0);
        p.setActivo(request.activo() != null ? request.activo() : true);
        p.setFechaCreacion(request.fechaCreacion() != null ? request.fechaCreacion() : LocalDateTime.now());
        return p;
    }
}
