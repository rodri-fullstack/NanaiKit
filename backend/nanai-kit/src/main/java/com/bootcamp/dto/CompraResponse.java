package com.bootcamp.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CompraResponse(
        Long id,
        LocalDateTime fechaCompra,
        Double total,
        String formaPago,
        String estadoCompra,
        Long clienteId,
        List<CompraDetalleResponse> detalles
) {}
