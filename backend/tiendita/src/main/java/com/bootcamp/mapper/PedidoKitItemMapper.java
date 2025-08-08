package com.bootcamp.mapper;


import com.bootcamp.dto.PedidoKitResponse;
import com.bootcamp.model.Kit;
import com.bootcamp.model.PedidoKit;

public class PedidoKitItemMapper {

    public static PedidoKitResponse toResponse(PedidoKit pk) {
        if (pk == null) return null;
        return PedidoKitResponse.builder()
                .kitId(pk.getKit() != null ? pk.getKit().getKitId() : null)
                .cantidad(pk.getCantidad())
                .build();
    }

    public static PedidoKit toEntity(PedidoKitResponse request) {
        if (request == null) return null;
        PedidoKit pk = new PedidoKit();
        if (request.kitId() != null) {
            Kit k = new Kit();
            k.setKitId(request.kitId());
            pk.setKit(k);
        }
        pk.setCantidad(request.cantidad());
        return pk;
    }
}
