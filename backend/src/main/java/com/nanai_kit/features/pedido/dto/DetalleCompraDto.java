package com.nanai_kit.features.pedido.dto;

import java.math.BigDecimal;

public record DetalleCompraDto(
        Long kitId,
        BigDecimal cantidad
) {}
