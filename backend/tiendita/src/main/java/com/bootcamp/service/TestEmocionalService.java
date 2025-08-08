package com.bootcamp.service;

import com.bootcamp.dto.TestEmocionalRequest;
import com.bootcamp.dto.TestEmocionalResponse;

import java.util.List;

public interface TestEmocionalService {

    List<TestEmocionalResponse> listarTodos();
    TestEmocionalResponse obtenerPorId(Long id);
    TestEmocionalResponse guardar(TestEmocionalRequest testEmocionalRequest);
    TestEmocionalResponse actualizar(Long id, TestEmocionalRequest testEmocionalRequest);
    void eliminar(Long id);

}