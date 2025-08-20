package com.nanai_kit.features.pedido.service;

import com.nanai_kit.exception.NotFoundException;

import com.nanai_kit.features.kit.model.Kit;
import com.nanai_kit.features.kit.repository.KitRepository;
import com.nanai_kit.features.pedido.dto.DetalleCompraDto;
import com.nanai_kit.features.pedido.dto.PedidoRequest;
import com.nanai_kit.features.pedido.dto.PedidoResponse;
import com.nanai_kit.features.pedido.mapper.PedidoMapper;
import com.nanai_kit.features.pedido.model.Pedido;
import com.nanai_kit.features.pedido.repository.PedidoRepository;
import com.nanai_kit.features.pedidoDetalle.dto.PedidoDetalleResponse;
import com.nanai_kit.features.pedidoDetalle.model.PedidoDetalle;
import com.nanai_kit.features.usuario.model.Usuario;
import com.nanai_kit.features.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final KitRepository kitRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Pedido> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Pedido crearCompra(Long usuarioId, List<DetalleCompraDto> detalleCompraDto) {
        Pedido compra = new Pedido();
        List<PedidoDetalle> compraDetalle = new ArrayList<>();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new NotFoundException("Usuario no encontrado"));

        for (DetalleCompraDto detalleCompra : detalleCompraDto) {
            Kit kit = kitRepository.findById(detalleCompra.kitId()).orElseThrow(()
                    -> new NotFoundException("Kit no encontrado"));

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(compra);
            detalle.setKit(kit);
            detalle.setCantidad(detalleCompra.cantidad());
            detalle.setPrecioUnitario(kit.getPrecio());
            detalle.setNombreKit(kit.getNombre());
            compraDetalle.add(detalle);
        }
        compra.setDetalles(compraDetalle);
        compra.setUsuario(usuario);

        compra.setTotal(calculaTotalCompra(compraDetalle));

        compra = repository.save(compra);
        return compra;
    }

    private BigDecimal calculaTotalCompra(List<PedidoDetalle> compraDetalle) {
        BigDecimal totalCompra = BigDecimal.ZERO;
        for (PedidoDetalle detalle : compraDetalle) {
            BigDecimal cantidad = detalle.getCantidad();
            BigDecimal subtotal = detalle.getPrecioUnitario().multiply(cantidad);
            totalCompra = totalCompra.add(subtotal);
        }
        return totalCompra;
    }

    @Override
    public PedidoResponse actualizar(Long id, PedidoRequest requestDTO) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        Usuario usuario = usuarioRepository.findById(requestDTO.clienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        pedido.setUsuario(usuario);
        pedido.setTotal(requestDTO.total());

        if (requestDTO.detalles() != null && !requestDTO.detalles().isEmpty()) {
            pedido.getDetalles().clear();

            List<PedidoDetalle> nuevosDetalles = requestDTO.detalles().stream()
                    .map(detalleDto -> PedidoDetalle.builder()
                            .pedido(pedido)
                            .kit(kitRepository.findById(detalleDto.kitId())
                                    .orElseThrow(() -> new NotFoundException("Kit no encontrado: " + detalleDto.kitId())))
                            .cantidad(detalleDto.cantidad())
                            .precioUnitario(detalleDto.precioUnitario())
                            .build())
                    .toList();

            pedido.getDetalles().addAll(nuevosDetalles);
        }

        Pedido pedidoGuardado = repository.save(pedido);

        // ✅ Crear response manual con nombres exactos de tu entidad
        return new PedidoResponse(
                pedidoGuardado.getIdPedido(),        // ← idPedido
                pedidoGuardado.getFechaCreacion(),   // ← fechaCreacion
                pedidoGuardado.getTotal(),           // ← total
                pedidoGuardado.getEstado().toString(), // ← estado (enum a String)
                pedidoGuardado.getUsuario().getId(), // ← usuario.id
                mapearDetalles(pedidoGuardado.getDetalles()) // ← detalles mapeados
        );
    }

    // Método helper para mapear detalles sin proxies
    private List<PedidoDetalleResponse> mapearDetalles(List<PedidoDetalle> detalles) {
        return detalles.stream()
                .map(detalle -> {
                    // Crear Kit sin proxies de Hibernate
                    Kit kitLimpio = Kit.builder()
                            .id(detalle.getKit().getId())
                            .nombre(detalle.getKit().getNombre())
                            // Agrega otros campos básicos del Kit que necesites
                            .build();

                    return new PedidoDetalleResponse(
                            detalle.getIdPedidoDetalle(),
                            detalle.getCantidad(),
                            detalle.getPrecioUnitario(),
                            kitLimpio
                    );
                })
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Pedido no encontrado");
        repository.deleteById(id);
    }

}
