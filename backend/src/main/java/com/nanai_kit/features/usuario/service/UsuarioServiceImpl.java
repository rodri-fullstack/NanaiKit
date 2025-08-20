package com.nanai_kit.features.usuario.service;

import com.nanai_kit.exception.BadRequestException;
import com.nanai_kit.exception.NotFoundException;
import com.nanai_kit.features.usuario.dto.UsuarioRequestDTO;
import com.nanai_kit.features.usuario.dto.UsuarioResponseDTO;
import com.nanai_kit.features.usuario.mapper.UsuarioMapper;
import com.nanai_kit.features.usuario.model.Usuario;
import com.nanai_kit.features.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO crear(UsuarioRequestDTO requestDTO) {
        // Validaciones de “crear” sin grupos
        if (isBlank(requestDTO.nombre()) || isBlank(requestDTO.email()) || isBlank(requestDTO.contrasena())) {
            throw new BadRequestException("nombre, email y contraseña son obligatorios");
        }
        if (repository.existsByEmailIgnoreCase(requestDTO.email())) {
            throw new BadRequestException("El email ya está registrado");
        }

        String hash = passwordEncoder.encode(requestDTO.contrasena());
        Usuario entity = UsuarioMapper.toNewEntity(requestDTO, hash);
        return UsuarioMapper.toResponse(repository.save(entity));
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listar() {
        return repository.findAll().stream().map(UsuarioMapper::toResponse).toList();
    }

    @Override
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        // si viene email y cambia, validar unicidad
        if (requestDTO.email() != null && !requestDTO.email().equalsIgnoreCase(usuario.getEmail())) {
            if (repository.existsByEmailIgnoreCase(requestDTO.email())) {
                throw new BadRequestException("El email ya está en uso");
            }
        }

        String newHash = null;
        if (!isBlank(requestDTO.contrasena())) {
            newHash = passwordEncoder.encode(requestDTO.contrasena());
        }

        UsuarioMapper.applyUpdates(usuario, requestDTO, newHash);
        return UsuarioMapper.toResponse(repository.save(usuario));
    }

    @Override
    public void desactivar(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        usuario.setActivo(false);
        repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Usuario no encontrado");
        repository.deleteById(id);
    }

    private static boolean isBlank(String string) {
        return string == null || string.isBlank();
    }
}
