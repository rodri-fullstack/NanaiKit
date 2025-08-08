package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record ProductoRequest(
        String nombre,
        String tipo,
        String descripcion,
        Integer stock,
        Boolean activo,
        java.time.LocalDateTime fechaCreacion
) {}
