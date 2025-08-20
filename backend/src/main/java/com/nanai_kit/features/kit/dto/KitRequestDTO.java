package com.nanai_kit.features.kit.dto;

import java.math.BigDecimal;

public record KitRequestDTO(
        String nombre,
        String nivelAnsiedadKit,
        String codigo,
        BigDecimal precio,
        String descripcionBreve,
        Boolean activo
) {}
