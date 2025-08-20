package com.nanai_kit.features.pedidoDetalle.dto;

import java.math.BigDecimal;

public record PedidoDetalleRequest(
        Long kitId,
        BigDecimal cantidad,
        BigDecimal precioUnitario
) {}
