package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record ProductoResponse(
        Long productoId,
        String nombre,
        String tipo,
        String descripcion,
        Integer stock,
        Boolean activo,
        java.time.LocalDateTime fechaCreacion
) {}