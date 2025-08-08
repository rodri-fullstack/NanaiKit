package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record PedidoKitResponse(
        Long pedidoKitId,
        Long pedidoId,
        Long kitId,
        Integer cantidad
) {}