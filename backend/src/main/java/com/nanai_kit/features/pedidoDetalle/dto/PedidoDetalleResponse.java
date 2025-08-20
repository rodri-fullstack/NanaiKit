package com.nanai_kit.features.pedidoDetalle.dto;

import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.model.Kit;

import java.math.BigDecimal;

public record PedidoDetalleResponse(
        Long id,
        BigDecimal cantidad,
        BigDecimal precioUnitario,
        Kit kit
) {}
