package com.bootcamp.dto;

import java.util.List;

public record CompraRequest(
        Long clienteId,
        Double total,
        String formaPago,
        List<CompraDetalleRequest> detalles
) {}
