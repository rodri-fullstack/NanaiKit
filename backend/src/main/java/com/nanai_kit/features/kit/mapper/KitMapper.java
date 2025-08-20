package com.nanai_kit.features.kit.mapper;

import com.nanai_kit.features.kit.dto.KitRequestDTO;
import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.model.Kit;
import com.nanai_kit.features.kit.model.NivelAnsiedadKit;
import com.nanai_kit.features.producto.dto.ProductoRequest;
import com.nanai_kit.features.producto.dto.ProductoResponse;
import com.nanai_kit.features.producto.model.Producto;

import java.util.stream.Collectors;

public class KitMapper {

    public static Kit toNewEntity(KitRequestDTO request) {
        return Kit.builder()
                .nombre(request.nombre())
                .nivel(NivelAnsiedadKit.valueOf(request.nivelAnsiedadKit()))
                .precio(request.precio())
                .descripcionBreve(request.descripcionBreve())
                .activo(request.activo() == null ? true : request.activo())
                .build();
    }

    public static void updateEntity(Kit kit, KitRequestDTO request) {
        if (kit == null || request == null) return;
        kit.setNombre(request.nombre());
        kit.setCodigo(request.codigo());
        kit.setNivel(NivelAnsiedadKit.valueOf(request.nivelAnsiedadKit()));
        kit.setPrecio(request.precio() != null ? request.precio() : kit.getPrecio());
        kit.setDescripcionBreve(request.descripcionBreve());
        kit.setActivo(request.activo());
    }

    public static KitResponseDTO toResponse(Kit kit) {
        if (kit == null) return null;
        return new KitResponseDTO(
                kit.getId(),
                kit.getCodigo(),
                kit.getNombre(),
                kit.getNivel(),
                kit.getPrecio(),
                kit.getDescripcionBreve(),
                kit.getActivo(),
                kit.getKitProductos().stream()
                        .map(kitProducto -> kitProducto.getProducto())
                        .collect(Collectors.toList())
        );
    }

}

