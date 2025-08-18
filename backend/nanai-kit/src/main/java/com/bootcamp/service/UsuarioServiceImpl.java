package com.bootcamp.service;

import com.bootcamp.model.Rol;
import com.bootcamp.model.Usuario;
import com.bootcamp.enums.NombreRol;
import com.bootcamp.exception.ApiException;
import com.bootcamp.repository.RolRepository;
import com.bootcamp.repository.UsuarioRepository;
import com.bootcamp.security.dto.RegisterRequest;
import com.bootcamp.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Override
    public boolean registrarUsuario(RegisterRequest request) {
        if (usuarioRepo.existsByEmail(request.email())) {
            throw new ApiException("El email ya está registrado"); // Puedes usar EmailAlreadyUsedException
        }

        Set<Rol> roles = new HashSet<>();

        for (NombreRol rol : request.roles()) {
            Rol rolUsuario = rolRepo.findByNombre(rol)
                    .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no encontrado"));
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
        nuevoUsuario.setTipoUsuario(request.tipoUsuario());
        nuevoUsuario.setRoles(roles);

        try {
            usuarioRepo.save(nuevoUsuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new ApiException(e.getMessage());
        }
    }
}
