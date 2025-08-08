package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record KitResponse(
        Long kitId,
        String nombre,
        String nivelAnsiedad,
        String descripcion,
        java.math.BigDecimal precio,
        Integer stock,
        Boolean activo
) {}