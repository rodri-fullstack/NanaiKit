package com.bootcamp.mapper;

import com.bootcamp.dto.KitProductoRequest;
import com.bootcamp.dto.KitProductoResponse;
import com.bootcamp.model.Kit;
import com.bootcamp.model.KitProducto;
import com.bootcamp.model.Producto;

public class KitProductoMapper {

    public static KitProductoResponse toResponse(KitProducto kp) {
        if (kp == null) return null;
        return KitProductoResponse.builder()
                .kitProductoId(kp.getKitProductoId())
                .kitId(kp.getKit() != null ? kp.getKit().getKitId() : null)
                .kitNombre(kp.getKit() != null ? kp.getKit().getNombre() : null)
                .productoId(kp.getProducto() != null ? kp.getProducto().getProductoId() : null)
                .productoNombre(kp.getProducto() != null ? kp.getProducto().getNombre() : null)
                .build();
    }

    public static KitProducto toEntity(KitProductoRequest request) {
        if (request == null) return null;
        KitProducto kp = new KitProducto();
        if (request.kitId() != null) {
            Kit k = new Kit();
            k.setKitId(request.kitId());
            kp.setKit(k);
        }
        if (request.productoId() != null) {
            Producto p = new Producto();
            p.setProductoId(request.productoId());
            kp.setProducto(p);
        }
        return kp;
    }
}
