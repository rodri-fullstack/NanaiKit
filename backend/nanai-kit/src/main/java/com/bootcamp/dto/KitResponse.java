package com.bootcamp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record KitResponse(
        Long id,
        String nombre,
        String nivelAnsiedad,
        String descripcion,
        Double precio,
        Integer stock,
        Boolean activo,
        String tipoContenidoDigital,
        String urlContenido,
        LocalDateTime fechaCreacion,
        List<ProductoResponse> productos
) {}
