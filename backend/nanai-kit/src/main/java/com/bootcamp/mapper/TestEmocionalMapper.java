package com.bootcamp.mapper;

import com.bootcamp.dto.TestEmocionalRequest;
import com.bootcamp.dto.TestEmocionalResponse;
import com.bootcamp.model.TestEmocional;

public class TestEmocionalMapper {

    public static TestEmocionalResponse toResponse(TestEmocional t) {
        if (t == null) return null;
        return new TestEmocionalResponse(
                t.getId(),
                t.getResultado(),
                t.getFecha()
        );
    }

    public static TestEmocional toEntity(TestEmocionalRequest req) {
        if (req == null) return null;
        TestEmocional t = new TestEmocional();
        t.setResultado(req.resultado());
        if (req.usuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(req.usuarioId()); // referencia por id (sin fetch)
            t.setUsuario(u);
        }
        return t;
    }
}
