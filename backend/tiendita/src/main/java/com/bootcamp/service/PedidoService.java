package com.bootcamp.service;

import com.bootcamp.dto.PedidoRequest;
import com.bootcamp.dto.PedidoResponse;

import java.util.List;

public interface PedidoService {

    List<PedidoResponse> listarTodos();
    PedidoResponse obtenerPorId(Long id);
    PedidoResponse guardar(PedidoRequest pedidoRequest);
    PedidoResponse actualizar(Long id, PedidoRequest pedidoRequest);
    void eliminar(Long id);

}