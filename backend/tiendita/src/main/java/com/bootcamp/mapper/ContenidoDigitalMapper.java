package com.bootcamp.mapper;

import com.bootcamp.dto.ContenidoDigitalRequest;
import com.bootcamp.dto.ContenidoDigitalResponse;
import com.bootcamp.model.ContenidoDigital;
import com.bootcamp.model.Kit;

public class ContenidoDigitalMapper {

    public static ContenidoDigitalResponse toResponse(ContenidoDigital c) {
        if (c == null) return null;
        return ContenidoDigitalResponse.builder()
                .contenidoId(c.getContenidoId())
                .kitId(c.getKit() != null ? c.getKit().getKitId() : null)
                .tipoContenido(c.getTipoContenido())
                .url(c.getUrl())
                .build();
    }

    public static ContenidoDigital toEntity(ContenidoDigitalRequest request) {
        if (request == null) return null;
        ContenidoDigital c = new ContenidoDigital();
        if (request.kitId() != null) {
            Kit k = new Kit();
            k.setKitId(request.kitId());
            c.setKit(k);
        }
        c.setContenidoId(request.kitId());
        c.setUrl(request.url());
        return c;
    }
}
