package com.bootcamp.dto;

import java.math.BigDecimal;

public record CompraDetalleRequest(
        Long kitId,
        Integer cantidad,
        Double precioUnitario
) {}
