package com.bootcamp.service;


import com.bootcamp.dto.ContenidoDigitalRequest;
import com.bootcamp.dto.ContenidoDigitalResponse;

import java.util.List;

public interface ContenidoDigitalService {

    List<ContenidoDigitalResponse> listarTodos();
    ContenidoDigitalResponse obtenerPorId(Long id);
    ContenidoDigitalResponse guardar(ContenidoDigitalRequest contenidoDigitalRequest);
    ContenidoDigitalResponse actualizar(Long id, ContenidoDigitalRequest contenidoDigitalRequest);
    void eliminar(Long id);
}