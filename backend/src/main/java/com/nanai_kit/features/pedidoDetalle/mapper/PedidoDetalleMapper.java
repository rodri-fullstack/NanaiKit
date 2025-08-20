package com.nanai_kit.features.pedidoDetalle.mapper;


import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.mapper.KitMapper;
import com.nanai_kit.features.kit.model.Kit;
import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleRequest;
import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleResponse;
import com.nanai_kit.features.pedidoDetalle.model.PedidoDetalle;

import java.math.BigDecimal;

public class PedidoDetalleMapper {

    public static PedidoDetalleResponse toResponse(PedidoDetalle detalle) {
        if (detalle == null) return null;
        Kit kit = detalle.getKit();
        return new PedidoDetalleResponse(
                detalle.getIdPedidoDetalle(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                kit
        );
    }

    public static PedidoDetalle toEntity(PedidoDetalleRequest request) {
        if (request == null) return null;
        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setCantidad(request.cantidad() != null ? request.cantidad() : BigDecimal.valueOf(1));
        detalle.setPrecioUnitario(request.precioUnitario() != null ? request.precioUnitario() : BigDecimal.ZERO);

        if (request.kitId() != null) {
            Kit kit = new Kit();
            kit.setId(request.kitId()); // referencia por id
            detalle.setKit(kit);
        }
        return detalle;
    }
}
