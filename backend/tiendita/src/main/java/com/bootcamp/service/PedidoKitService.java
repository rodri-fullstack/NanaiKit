package com.bootcamp.service;


import com.bootcamp.dto.PedidoKitRequest;
import com.bootcamp.dto.PedidoKitResponse;

import java.util.List;

public interface PedidoKitService {

    List<PedidoKitResponse> listarTodos();
    PedidoKitResponse obtenerPorId(Long id);
    PedidoKitResponse guardar(PedidoKitRequest pedidoKitRequest);
    PedidoKitResponse actualizar(Long id, PedidoKitRequest pedidoKitRequest);
    void eliminar(Long id);

}