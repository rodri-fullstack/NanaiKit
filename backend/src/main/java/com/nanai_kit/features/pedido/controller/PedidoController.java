package com.nanai_kit.features.pedido.controller;

import com.nanai_kit.exception.ApiException;
import com.nanai_kit.exception.NotFoundException;
import com.nanai_kit.features.pedido.dto.DetalleCompraDto;
import com.nanai_kit.features.pedido.dto.PedidoRequest;
import com.nanai_kit.features.pedido.dto.PedidoResponse;
import com.nanai_kit.features.pedido.model.Pedido;
import com.nanai_kit.features.pedido.service.PedidoServiceImpl;
import com.nanai_kit.features.usuario.model.Usuario;
import com.nanai_kit.features.usuario.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoServiceImpl pedidoService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Pedido> listarTodas() {
        try {
            return pedidoService.listarTodas();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(e.getMessage());
        }
    }

    @GetMapping("/{usuarioId}")
    public List<Pedido> buscarPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new NotFoundException("Usuario no encontrado"));
        return pedidoService.buscarPorUsuario(usuario.getId());
    }

    @Operation(description = "Con este endpoint generamos una compra", summary = "Generar compra")
    @PostMapping("/crear-pedido")
    public Pedido crearCompra(@RequestParam Long usuarioId, @RequestBody List<DetalleCompraDto> detalleCompraDto) {
        return pedidoService.crearCompra(usuarioId, detalleCompraDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody PedidoRequest request) {
        return ResponseEntity.ok(pedidoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
