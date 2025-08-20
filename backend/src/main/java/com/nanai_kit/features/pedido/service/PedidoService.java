package com.nanai_kit.features.pedido.service;


import com.nanai_kit.features.pedido.dto.DetalleCompraDto;
import com.nanai_kit.features.pedido.dto.PedidoRequest;
import com.nanai_kit.features.pedido.dto.PedidoResponse;
import com.nanai_kit.features.pedido.model.Pedido;
import com.nanai_kit.features.pedidoDetalle.model.EstadoPedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> buscarPorUsuario(Long usuarioId);
    List<Pedido> listarTodas();
    Pedido crearCompra(Long usuarioId, List<DetalleCompraDto> detalleCompraDto);
    PedidoResponse actualizar(Long id, PedidoRequest requestDTO);
    void eliminar(Long id);
}
