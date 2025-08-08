package com.bootcamp.service;


import com.bootcamp.dto.KitRequest;
import com.bootcamp.dto.KitResponse;

import java.util.List;

public interface KitService {

    List<KitResponse> listarTodos();
    KitResponse obtenerPorId(Long id);
    KitResponse guardar(KitRequest kitRequest);
    KitResponse actualizar(Long id, KitRequest kitRequest);
    void eliminar(Long id);

}