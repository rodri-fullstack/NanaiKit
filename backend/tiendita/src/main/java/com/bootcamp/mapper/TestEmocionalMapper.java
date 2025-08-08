package com.bootcamp.mapper;

import com.bootcamp.dto.TestEmocionalRequest;
import com.bootcamp.dto.TestEmocionalResponse;
import com.bootcamp.model.TestEmocional;
import com.bootcamp.model.Usuario;

public class TestEmocionalMapper {

    public static TestEmocionalResponse toResponse(TestEmocional testEmocional) {
        if (testEmocional == null) return null;
        return TestEmocionalResponse.builder()
                .testId(testEmocional.getTestId())
                .usuarioId(testEmocional.getUsuario() != null ? testEmocional.getUsuario().getUsuarioId() : null)
                .resultado(testEmocional.getResultado())
                .fecha(testEmocional.getFecha())
                .build();
    }

    public static TestEmocional toEntity(TestEmocionalRequest request) {
        if (request == null) return null;
        TestEmocional testEmocional = new TestEmocional();
        if (request.usuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(request.usuarioId());
            testEmocional.setUsuario(usuario);
        }
        testEmocional.setResultado(request.resultado());
        testEmocional.setFecha(request.fecha());
        return testEmocional;
    }
}
