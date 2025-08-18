package com.bootcamp.mapper;

import com.bootcamp.dto.KitRequest;
import com.bootcamp.dto.KitResponse;
import com.bootcamp.dto.ProductoResponse;
import com.bootcamp.model.Kit;

import java.util.List;
import java.util.Objects;

public class KitMapper {

    public static KitResponse toResponse(Kit k) {
        if (k == null) return null;

        List<ProductoResponse> productos = null;
        if (k.getProductos() != null) {
            productos = k.getProductos().stream()
                    .filter(Objects::nonNull)
                    .map(ProductoMapper::toResponse)
                    .toList();
        }

        return new KitResponse(
                k.getId(),
                k.getNombre(),
                k.getNivelAnsiedad(),
                k.getDescripcion(),
                k.getPrecio(),
                k.getStock(),
                k.getActivo(),
                k.getTipoContenidoDigital(),
                k.getUrlContenido(),
                k.getFechaCreacion(),
                productos
        );
    }

    public static Kit toEntity(KitRequest request) {
        if (request == null) return null;
        Kit k = new Kit();
        k.setNombre(request.nombre());
        k.setNivelAnsiedad(request.nivelAnsiedad());
        k.setDescripcion(request.descripcion());
        k.setPrecio(request.precio() != null ? request.precio() : 0);
        k.setStock(request.stock() != null ? request.stock() : 0);
        k.setTipoContenidoDigital(request.tipoContenidoDigital());
        k.setUrlContenido(request.urlContenido());
        return k;
    }

    public static void updateEntity(Kit k, KitRequest request) {
        if (k == null || request == null) return;
        k.setNombre(request.nombre());
        k.setNivelAnsiedad(request.nivelAnsiedad());
        k.setDescripcion(request.descripcion());
        k.setPrecio(request.precio() != null ? request.precio() : k.getPrecio());
        k.setStock(request.stock() != null ? request.stock() : k.getStock());
        k.setTipoContenidoDigital(request.tipoContenidoDigital());
        k.setUrlContenido(request.urlContenido());
    }
}
