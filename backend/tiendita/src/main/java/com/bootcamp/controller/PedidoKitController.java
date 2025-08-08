package com.bootcamp.controller;


import com.bootcamp.dto.PedidoKitRequest;
import com.bootcamp.dto.PedidoKitResponse;
import com.bootcamp.service.PedidoKitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos-kits")
public class PedidoKitController {

    private final PedidoKitService service;

    @GetMapping
    public ResponseEntity<List<PedidoKitResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoKitResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoKitResponse> guardar(@RequestBody PedidoKitRequest request) {
        return ResponseEntity.ok(service.guardar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoKitResponse> actualizar(@PathVariable Long id, @RequestBody PedidoKitRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}