package com.bootcamp.service;

import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.enums.NombreRol;
import com.bootcamp.exception.ApiException;
import com.bootcamp.mapper.UsuarioMapper;
import com.bootcamp.model.Rol;
import com.bootcamp.model.Usuario;
import com.bootcamp.repository.RolRepository;
import com.bootcamp.repository.UsuarioRepository;
import com.bootcamp.security.dto.RegisterRequest;
import com.bootcamp.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public boolean registrarUsuario(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ApiException("El email ya se encuentra registrado");
        }

        Set<Rol> roles = new HashSet<>();

        for (NombreRol rol : request.roles()) {
            Rol rolUsuario = rolRepository.findByNombre(rol)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado, intenta nuevamente"));
            roles.add(rolUsuario);
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.nombre());
        nuevoUsuario.setApellidos(request.apellidos());
        nuevoUsuario.setEmail(request.email());
        nuevoUsuario.setPassword(encoder.encode(request.password()));
        nuevoUsuario.setTelefono(request.telefono());
        nuevoUsuario.setDireccion(request.direccion());
        nuevoUsuario.setEdad(request.edad());
        nuevoUsuario.setGenero(request.genero());
        nuevoUsuario.setTipo_usuario(request.tipoUsuario());
        nuevoUsuario.setRoles(roles);

        try {
            usuarioRepository.save(nuevoUsuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }


    @Override
    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponse guardar(UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        return UsuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse actualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        return UsuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
