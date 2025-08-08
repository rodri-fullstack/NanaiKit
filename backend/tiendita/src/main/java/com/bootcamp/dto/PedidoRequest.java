package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record PedidoRequest(
        Long usuarioId,
        java.time.LocalDateTime fechaCreacion,
        java.math.BigDecimal total,
        java.util.List<PedidoKitResponse> items
) {}
