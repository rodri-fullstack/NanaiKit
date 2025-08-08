package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record ContenidoDigitalRequest(
        Long kitId,
        String tipoContenido,
        String url
) {}