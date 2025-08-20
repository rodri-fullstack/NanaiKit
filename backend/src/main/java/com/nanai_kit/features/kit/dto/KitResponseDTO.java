package com.nanai_kit.features.kit.dto;

import com.nanai_kit.features.kit.model.NivelAnsiedadKit;
import com.nanai_kit.features.producto.model.Producto;

import java.math.BigDecimal;
import java.util.List;

public record KitResponseDTO(
        Long id,
        String codigo,
        String nombre,
        NivelAnsiedadKit nivelAnsiedadKit,
        BigDecimal precio,
        String descripcionBreve,
        Boolean activo,
        List<Producto> productos
) {}