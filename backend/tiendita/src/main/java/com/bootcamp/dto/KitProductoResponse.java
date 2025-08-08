package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record KitProductoResponse(
        Long kitProductoId,
        Long kitId,
        String kitNombre,
        Long productoId,
        String productoNombre
) {}