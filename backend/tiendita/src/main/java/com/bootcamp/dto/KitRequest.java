package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record KitRequest(
        String nombre,
        String nivelAnsiedad,
        String descripcion,
        java.math.BigDecimal precio,
        Integer stock,
        Boolean activo
) {}
