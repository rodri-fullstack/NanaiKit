package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record ContenidoDigitalResponse(
        Long contenidoId,
        Long kitId,
        String tipoContenido,
        String url
) {}