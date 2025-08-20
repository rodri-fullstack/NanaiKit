package com.nanai_kit.features.kit.dto;

import java.util.List;

public record CreaKitDto(
        KitRequestDTO request,
        List<KitProductoDto> kitProductoDtos
) {
}
