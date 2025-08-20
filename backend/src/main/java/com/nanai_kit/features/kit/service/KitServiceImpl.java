package com.nanai_kit.features.kit.service;

import com.nanai_kit.exception.ApiException;
import com.nanai_kit.exception.NotFoundException;
import com.nanai_kit.features.kit.dto.CreaKitDto;
import com.nanai_kit.features.kit.dto.KitProductoDto;
import com.nanai_kit.features.kit.dto.KitRequestDTO;
import com.nanai_kit.features.kit.dto.KitResponseDTO;
import com.nanai_kit.features.kit.mapper.KitMapper;
import com.nanai_kit.features.kit.model.Kit;
import com.nanai_kit.features.kit.model.KitProducto;
import com.nanai_kit.features.kit.model.KitProductoId;
import com.nanai_kit.features.kit.model.NivelAnsiedadKit;
import com.nanai_kit.features.kit.repository.KitRepository;
import com.nanai_kit.features.producto.mapper.ProductoMapper;
import com.nanai_kit.features.producto.model.Producto;
import com.nanai_kit.features.producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitServiceImpl implements KitService {

    private final KitRepository repository;
    private final ProductoService productoService;

    @Override
    public List<Kit> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Kit crearKit(CreaKitDto dto) {

        if (repository.findByCodigo(dto.request().codigo()).isPresent()) {
            throw new ApiException("Existe un kit con el mismo codigo");
        }

        Kit kit = new Kit();
        kit.setCodigo(dto.request().codigo());
        kit.setNombre(dto.request().nombre());
        kit.setNivel(NivelAnsiedadKit.valueOf(dto.request().nivelAnsiedadKit()));
        kit.setPrecio(dto.request().precio());
        kit.setDescripcionBreve(dto.request().descripcionBreve());
        kit.setActivo(dto.request().activo());
        kit = repository.save(kit); // guardar primero para generar ID

        for (KitProductoDto kpDto : dto.kitProductoDtos()) {
            Producto producto = productoService.buscarPorId(kpDto.productoId());

            KitProducto kp = new KitProducto();
            kp.setProducto(producto);
            kp.setCantidad(kpDto.cantidad());
            kp.setId(new KitProductoId(kit.getId(), producto.getId()));

            kit.addKitProducto(kp); // usa el helper
        }

        return repository.save(kit);
    }

    @Override
    public Kit buscarPorId(Long id) {
        Kit kit = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Kit no encontrado"));

        return kit;
    }

    @Override
    public KitResponseDTO actualizar(Long id, KitRequestDTO request) {
        Kit kit = repository.findById(id).orElseThrow(() -> new NotFoundException("Kit no encontrado"));

        KitMapper.updateEntity(kit, request);
        return KitMapper.toResponse(repository.save(kit));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Kit no encontrado");
        repository.deleteById(id);
    }

}
