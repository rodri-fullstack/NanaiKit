package com.bootcamp.dto;

import java.time.LocalDateTime;

public record TestEmocionalResponse(
        Long id,
        String resultado,
        LocalDateTime fecha
) {}
