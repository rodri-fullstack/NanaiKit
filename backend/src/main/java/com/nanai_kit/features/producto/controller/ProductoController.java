package com.nanai_kit.features.producto.controller;

import com.nanai_kit.features.producto.dto.ProductoRequest;
import com.nanai_kit.features.producto.dto.ProductoResponse;
import com.nanai_kit.features.producto.model.Producto;
import com.nanai_kit.features.producto.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<ProductoResponse> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{productoId}")
    public Producto buscarPorId(@PathVariable Long productoId) {
        return service.buscarPorId(productoId);
    }

    @GetMapping("/sku/{sku}")
    public ProductoResponse buscarPorSku(@PathVariable String sku) {
        return service.buscarPorSku(sku);
    }

    @PostMapping
    public ProductoResponse crearProducto(@RequestBody ProductoRequest request) {
        return service.crearProducto(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
