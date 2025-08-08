package com.bootcamp.controller;


import com.bootcamp.dto.TestEmocionalRequest;
import com.bootcamp.dto.TestEmocionalResponse;
import com.bootcamp.service.TestEmocionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests-emocionales")
public class TestEmocionalController {

    private final TestEmocionalService service;

    @GetMapping
    public ResponseEntity<List<TestEmocionalResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestEmocionalResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TestEmocionalResponse> guardar(@RequestBody TestEmocionalRequest request) {
        return ResponseEntity.ok(service.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestEmocionalResponse> actualizar(@PathVariable Long id, @RequestBody TestEmocionalRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}