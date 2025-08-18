package com.bootcamp.mapper;

import com.bootcamp.dto.CompraDetalleRequest;
import com.bootcamp.dto.CompraDetalleResponse;
import com.bootcamp.dto.KitResponse;
import com.bootcamp.model.CompraDetalle;
import com.bootcamp.model.Kit;

public class CompraDetalleMapper {

    public static CompraDetalleResponse toResponse(CompraDetalle d) {
        if (d == null) return null;
        KitResponse kit = KitMapper.toResponse(d.getKit());
        return new CompraDetalleResponse(
                d.getId(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                kit
        );
    }

    public static CompraDetalle toEntity(CompraDetalleRequest req) {
        if (req == null) return null;
        CompraDetalle d = new CompraDetalle();
        d.setCantidad(req.cantidad() != null ? req.cantidad() : 1);
        d.setPrecioUnitario(req.precioUnitario() != null ? req.precioUnitario() : 0);

        if (req.kitId() != null) {
            Kit k = new Kit();
            k.setId(req.kitId()); // referencia por id
            d.setKit(k);
        }
        return d;
    }
}
