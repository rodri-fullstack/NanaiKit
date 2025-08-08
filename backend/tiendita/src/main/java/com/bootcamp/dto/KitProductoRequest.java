package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record KitProductoRequest(
        Long kitId,
        Long productoId
) {}
