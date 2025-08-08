package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record TestEmocionalResponse(
        Long testId,
        Long usuarioId,
        String resultado,
        java.time.LocalDate fecha
) {}