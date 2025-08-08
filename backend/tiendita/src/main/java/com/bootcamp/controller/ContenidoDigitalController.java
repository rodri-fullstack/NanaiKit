package com.bootcamp.controller;


import com.bootcamp.dto.ContenidoDigitalRequest;
import com.bootcamp.dto.ContenidoDigitalResponse;
import com.bootcamp.service.ContenidoDigitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contenidos-digitales")
public class ContenidoDigitalController {

    private final ContenidoDigitalService service;

    @GetMapping
    public ResponseEntity<List<ContenidoDigitalResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenidoDigitalResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContenidoDigitalResponse> guardar(@RequestBody ContenidoDigitalRequest request) {
        return ResponseEntity.ok(service.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenidoDigitalResponse> actualizar(@PathVariable Long id, @RequestBody ContenidoDigitalRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}