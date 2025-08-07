package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record ClienteResponse(
        Long usuario_id,
        String nombre,
        String direccion,
        String email
) {}
