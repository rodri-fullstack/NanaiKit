package com.nanai_kit.features.producto.dto;

import java.math.BigDecimal;

public record ProductoRequest(
        String nombre,
        String sku,
        BigDecimal costo,
        Integer stock,
        Boolean activo
) {}
