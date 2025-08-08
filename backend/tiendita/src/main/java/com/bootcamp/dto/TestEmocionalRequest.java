package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record TestEmocionalRequest(
        Long usuarioId,
        String resultado,
        java.time.LocalDate fecha
) {}