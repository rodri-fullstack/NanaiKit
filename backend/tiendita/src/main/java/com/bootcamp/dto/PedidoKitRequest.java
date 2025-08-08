package com.bootcamp.dto;

import lombok.Builder;

@Builder
public record PedidoKitRequest(
        Long pedidoId,
        Long kitId,
        Integer cantidad
) {}