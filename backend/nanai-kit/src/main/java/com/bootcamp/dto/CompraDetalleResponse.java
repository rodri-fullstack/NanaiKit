package com.bootcamp.dto;

public record CompraDetalleResponse(
        Long id,
        Integer cantidad,
        Double precioUnitario,
        KitResponse kit
) {}
