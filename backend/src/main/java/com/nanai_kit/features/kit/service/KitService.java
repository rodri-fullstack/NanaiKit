package com.nanai_kit.features.kit.service;

import com.nanai_kit.features.kit.dto.CreaKitDto;
import com.nanai_kit.features.kit.dto.KitRequestDTO;
import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.model.Kit;

import java.util.List;

public interface KitService {
    List<Kit> listarTodas();
    Kit crearKit(CreaKitDto dto);
    Kit buscarPorId(Long id);
    KitResponseDTO actualizar(Long id, KitRequestDTO request);
    void eliminar(Long id);
}
