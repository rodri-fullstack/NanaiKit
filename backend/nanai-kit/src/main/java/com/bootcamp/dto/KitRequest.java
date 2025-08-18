package com.bootcamp.dto;

import java.math.BigDecimal;

public record KitRequest(
        String nombre,
        String nivelAnsiedad,
        String descripcion,
        Double precio,
        Integer stock,
        String tipoContenidoDigital,
        String urlContenido
) {}
