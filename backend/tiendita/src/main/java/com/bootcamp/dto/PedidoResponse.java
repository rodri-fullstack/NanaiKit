package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record PedidoResponse(
        Long pedidoId,
        Long usuarioId,
        java.time.LocalDateTime fechaCreacion,
        java.math.BigDecimal total,
        java.util.List<PedidoKitResponse> items
) {}
