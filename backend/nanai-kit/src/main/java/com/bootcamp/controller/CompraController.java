package com.bootcamp.controller;

import com.bootcamp.dto.CompraResponse;
import com.bootcamp.dto.DetalleCompraDto;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.model.Usuario;
import com.bootcamp.repository.UsuarioRepository;
import com.bootcamp.service.CompraServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/compra")
public class CompraController {

    private final CompraServiceImpl compraService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<CompraResponse> listarTodas() {
        return compraService.listarTodas();
    }

    @GetMapping("/{usuarioId}")
    public List<CompraResponse> buscarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new NotFoundException("Usuario no encontrado"));
        return compraService.buscarPorUsuario(usuario.getId());
    }

    @Operation(description = "Con este endpoint generamos una compra", summary = "Generar compra")
    @PostMapping("/crear-compra")
    public CompraResponse crearCompra(@RequestParam Long usuarioId, @RequestParam String formaPago, @RequestBody List<DetalleCompraDto> detalleCompraDto) {
        return compraService.crearCompra(usuarioId, formaPago, detalleCompraDto);
    }

}
