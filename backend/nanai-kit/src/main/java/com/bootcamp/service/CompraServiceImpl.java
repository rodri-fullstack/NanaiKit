package com.bootcamp.service;

import com.bootcamp.dto.CompraResponse;
import com.bootcamp.dto.DetalleCompraDto;
import com.bootcamp.model.Compra;
import com.bootcamp.model.CompraDetalle;
import com.bootcamp.model.Kit;
import com.bootcamp.model.Usuario;
import com.bootcamp.enums.EstadoCompra;
import com.bootcamp.enums.FormaPago;
import com.bootcamp.exception.ApiException;
import com.bootcamp.exception.NotFoundException;
import com.bootcamp.mapper.CompraMapper;
import com.bootcamp.repository.CompraRepository;
import com.bootcamp.repository.KitRepository;
import com.bootcamp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final CompraRepository repository;
    private final KitRepository kitRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<CompraResponse> buscarPorUsuario(Long usuarioId) {
        return repository.findByClienteId(usuarioId).stream().map(CompraMapper::toResponse).toList();
    }

    @Override
    public List<CompraResponse> listarTodas() {
        return repository.findAll().stream().map(CompraMapper::toResponse).toList();
    }

    @Override
    public CompraResponse crearCompra(Long usuarioId, String formaPago, List<DetalleCompraDto> detalleCompraDto) {
        Compra compra = new Compra();
        List<CompraDetalle> compraDetalle = new ArrayList<>();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new NotFoundException("Usuario no encontrado"));

        for (DetalleCompraDto detalleCompra : detalleCompraDto) {
            Kit kit = kitRepository.findById(detalleCompra.kitId()).orElseThrow(()
                    -> new NotFoundException("Kit no encontrado"));

            CompraDetalle detalle = new CompraDetalle();
            detalle.setCompra(compra);
            detalle.setKit(kit);
            detalle.setCantidad(detalleCompra.cantidad());
            detalle.setPrecioUnitario(kit.getPrecio());

            compraDetalle.add(detalle);
        }
        compra.setDetalles(compraDetalle);
        compra.setCliente(usuario);

        switch (formaPago.toLowerCase()) {
            case "efectivo":
                compra.setFormaPago(FormaPago.efectivo);
                break;
            case "transferencia":
                compra.setFormaPago(FormaPago.transferencia);
                break;
            case "debito":
                compra.setFormaPago(FormaPago.debito);
                break;
            case "credito":
                compra.setFormaPago(FormaPago.credito);
                break;
            default:
                throw new ApiException("Ingrese una forma de pago");
        }
        compra.setEstadoCompra(EstadoCompra.pendiente);
        compra.setTotal(calculaTotalCompra(compraDetalle));

        compra = repository.save(compra);
        return CompraMapper.toResponse(compra);
    }

    private Double calculaTotalCompra(List<CompraDetalle> compraDetalle) {
        Double totalCompra = 0.0;
        for (CompraDetalle detalle : compraDetalle) {
            totalCompra = totalCompra + (detalle.getCantidad() * detalle.getPrecioUnitario().doubleValue());
        }
        return totalCompra;
    }
}
