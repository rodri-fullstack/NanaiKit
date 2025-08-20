package com.nanai_kit.features.kit.controller;

import com.nanai_kit.features.kit.dto.CreaKitDto;
import com.nanai_kit.features.kit.dto.KitRequestDTO;
import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.model.Kit;
import com.nanai_kit.features.kit.service.KitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kit")
public class KitController {

    private final KitService service;

    @GetMapping
    public List<Kit> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Kit buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Kit crearKit(@RequestBody CreaKitDto dto) {
        return service.crearKit(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody KitRequestDTO request) {
        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
