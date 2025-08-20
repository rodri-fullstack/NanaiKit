package com.nanai_kit.features.pedido.dto;

import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleRequest;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest(
        Long clienteId,
        BigDecimal total,
        List<PedidoDetalleRequest> detalles
) {}
