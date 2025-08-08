package com.bootcamp.mapper;

import com.bootcamp.dto.PedidoKitResponse;
import com.bootcamp.dto.PedidoRequest;
import com.bootcamp.dto.PedidoResponse;
import com.bootcamp.model.Pedido;
import com.bootcamp.model.PedidoKit;
import com.bootcamp.model.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    public static PedidoResponse toResponse(Pedido p) {
        if (p == null) return null;

        List<PedidoKitResponse> items = new ArrayList<>();
        if (p.getItems() != null) {
            for (PedidoKit pk : p.getItems()) {
                items.add(PedidoKitItemMapper.toResponse(pk));
            }
        }

        return PedidoResponse.builder()
                .pedidoId(p.getPedidoId())
                .usuarioId(p.getUsuario() != null ? p.getUsuario().getUsuarioId() : null)
                .fechaCreacion(p.getFechaCreacion())
                .total(p.getTotal())
                .items(items)
                .build();
    }

    public static Pedido toEntity(PedidoRequest request) {
        if (request == null) return null;

        Pedido p = new Pedido();

        if (request.usuarioId() != null) {
            Usuario u = new Usuario();
            u.setUsuarioId(request.usuarioId());
            p.setUsuario(u);
        }

        p.setFechaCreacion(request.fechaCreacion() != null ? request.fechaCreacion() : LocalDateTime.now());
        p.setTotal(request.total());

        List<PedidoKit> lista = new ArrayList<>();
        if (request.items() != null) {
            for (PedidoKitResponse it : request.items()) {
                PedidoKit pk = PedidoKitItemMapper.toEntity(it);
                pk.setPedido(p);
                lista.add(pk);
            }
        }
        p.setItems(lista);

        return p;
    }
}
