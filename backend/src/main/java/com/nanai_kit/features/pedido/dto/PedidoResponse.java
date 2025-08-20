package com.nanai_kit.features.pedido.dto;

import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        LocalDateTime fechaCompra,
        BigDecimal total,
        String estadoCompra,
        Long clienteId,
        List<PedidoDetalleResponse> detalles
) {}
