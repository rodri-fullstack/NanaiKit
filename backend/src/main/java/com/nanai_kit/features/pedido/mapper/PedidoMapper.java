package com.nanai_kit.features.pedido.mapper;
import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleRequest;
import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleResponse;
import com.nanai_kit.features.pedido.dto.PedidoRequest;
import com.nanai_kit.features.pedido.dto.PedidoResponse;
import com.nanai_kit.features.pedido.model.Pedido;
import com.nanai_kit.features.pedidoDetalle.mapper.PedidoDetalleMapper;
import com.nanai_kit.features.pedidoDetalle.model.PedidoDetalle;
import com.nanai_kit.features.usuario.dto.UsuarioResponseDTO;
import com.nanai_kit.features.usuario.mapper.UsuarioMapper;
import com.nanai_kit.features.usuario.model.Usuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoMapper {

    public static PedidoResponse toResponse(Pedido pedido) {
        if (pedido == null) return null;

        UsuarioResponseDTO cliente = UsuarioMapper.toResponse(pedido.getUsuario());

        List<PedidoDetalleResponse> detalles = null;
        if (pedido.getDetalles() != null) {
            detalles = pedido.getDetalles().stream()
                    .filter(Objects::nonNull)
                    .map(PedidoDetalleMapper::toResponse)
                    .toList();
        }

        return new PedidoResponse(
                pedido.getIdPedido(),
                pedido.getFechaCreacion(),
                pedido.getTotal(),
                pedido.getEstado().toString(),
                cliente.id(),
                detalles
        );
    }

    public static Pedido toEntity(PedidoRequest request) {
        if (request == null) return null;

        Pedido pedido = new Pedido();

        // Cliente
        if (request.clienteId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(request.clienteId()); // referencia por id
            pedido.setUsuario(usuario);
        }

        pedido.setTotal(request.total() != null ? request.total() : BigDecimal.ZERO);

        // Detalles
//        if (request.detalles() != null && !request.detalles().isEmpty()) {
//            List<PedidoDetalle> items = new ArrayList<>();
//            for (PedidoDetalleRequest detalleRequest : request.detalles()) {
//                PedidoDetalle detalle = PedidoDetalleMapper.toEntity(detalleRequest);
//                det.setCompra(c); // back-reference
//                items.add(det);
//            }
//            c.setDetalles(items);
//        }

        if (request.detalles() != null && !request.detalles().isEmpty()) {
            List<PedidoDetalle> items = new ArrayList<>();
            for (PedidoDetalleRequest detalleRequest : request.detalles()) {
                PedidoDetalle detalle = PedidoDetalleMapper.toEntity(detalleRequest);
                detalle.setPedido(pedido); // back-reference
                items.add(detalle);
            }
            pedido.setDetalles(items);
        }
        return pedido;
    }
}
