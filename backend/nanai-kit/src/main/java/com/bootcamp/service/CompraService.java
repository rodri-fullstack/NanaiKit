package com.bootcamp.service;

import com.bootcamp.dto.CompraResponse;
import com.bootcamp.dto.DetalleCompraDto;

import java.util.List;

public interface CompraService {

    List<CompraResponse> buscarPorUsuario(Long usuarioId);
    List<CompraResponse> listarTodas();
    CompraResponse crearCompra(Long usuarioId, String formaPago, List<DetalleCompraDto> detalleCompraDto);

}
