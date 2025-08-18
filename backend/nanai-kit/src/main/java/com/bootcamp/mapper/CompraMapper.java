package com.bootcamp.mapper;

import com.bootcamp.dto.*;
import com.bootcamp.model.Compra;
import com.bootcamp.model.CompraDetalle;
import com.bootcamp.enums.FormaPago;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompraMapper {

    public static CompraResponse toResponse(Compra c) {
        if (c == null) return null;

        UsuarioResponse cliente = UsuarioMapper.toResponse(c.getCliente());

        List<CompraDetalleResponse> detalles = null;
        if (c.getDetalles() != null) {
            detalles = c.getDetalles().stream()
                    .filter(Objects::nonNull)
                    .map(CompraDetalleMapper::toResponse)
                    .toList();
        }

        return new CompraResponse(
                c.getId(),
                c.getFechaCompra(),
                c.getTotal(),
                c.getFormaPago().toString(),
                c.getEstadoCompra().toString(),
                cliente.id(),
                detalles
        );
    }

    public static Compra toEntity(CompraRequest req) {
        if (req == null) return null;

        Compra c = new Compra();

        // Cliente
        if (req.clienteId() != null) {
            Usuario u = new Usuario();
            u.setId(req.clienteId()); // referencia por id
            c.setCliente(u);
        }

        c.setTotal(req.total() != null ? req.total() : 0);
        c.setFormaPago(FormaPago.valueOf(req.formaPago() != null ? req.formaPago() : "efectivo"));
        // estado_compra por defecto en entidad ("pendiente")

        // Detalles
        if (req.detalles() != null && !req.detalles().isEmpty()) {
            List<CompraDetalle> items = new ArrayList<>();
            for (CompraDetalleRequest dreq : req.detalles()) {
                CompraDetalle det = CompraDetalleMapper.toEntity(dreq);
                det.setCompra(c); // back-reference
                items.add(det);
            }
            c.setDetalles(items);
        }

        return c;
    }
}
