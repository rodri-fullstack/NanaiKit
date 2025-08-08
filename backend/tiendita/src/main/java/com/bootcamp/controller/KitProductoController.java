package com.bootcamp.controller;


import com.bootcamp.dto.KitProductoRequest;
import com.bootcamp.dto.KitProductoResponse;
import com.bootcamp.service.KitProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kits-productos")
public class KitProductoController {

    private final KitProductoService service;

    @GetMapping
    public ResponseEntity<List<KitProductoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<KitProductoResponse> guardar(@RequestBody KitProductoRequest request) {
        return ResponseEntity.ok(service.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitProductoResponse> actualizar(@PathVariable Long id, @RequestBody KitProductoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}