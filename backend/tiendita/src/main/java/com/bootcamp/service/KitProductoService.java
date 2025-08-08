package com.bootcamp.service;

import com.bootcamp.dto.KitProductoRequest;
import com.bootcamp.dto.KitProductoResponse;

import java.util.List;

public interface KitProductoService {

    List<KitProductoResponse> listarTodos();
    KitProductoResponse obtenerPorId(Long id);
    KitProductoResponse guardar(KitProductoRequest kitProductoRequest);
    KitProductoResponse actualizar(Long id, KitProductoRequest kitProductoRequest);
    void eliminar(Long id);

}