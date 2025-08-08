package com.bootcamp.mapper;


import com.bootcamp.dto.KitRequest;
import com.bootcamp.dto.KitResponse;
import com.bootcamp.model.Kit;

import java.math.BigDecimal;

public class KitMapper {

    public static KitResponse toResponse(Kit k) {
        if (k == null) return null;
        return KitResponse.builder()
                .kitId(k.getKitId())
                .nombre(k.getNombre())
                .nivelAnsiedad(k.getNivelAnsiedad())
                .descripcion(k.getDescripcion())
                .precio(k.getPrecio())
                .stock(k.getStock())
                .activo(k.getActivo())
                .build();
    }

    public static Kit toEntity(KitRequest request) {
        if (request == null) return null;
        Kit k = new Kit();
        k.setNombre(request.nombre());
        k.setNivelAnsiedad(request.nivelAnsiedad());
        k.setDescripcion(request.descripcion());
        k.setPrecio(request.precio() != null ? request.precio() : BigDecimal.ZERO);
        k.setStock(request.stock() != null ? request.stock() : 0);
        k.setActivo(request.activo() != null ? request.activo() : true);
        return k;
    }
}
