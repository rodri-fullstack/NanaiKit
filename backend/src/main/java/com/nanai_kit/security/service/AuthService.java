package com.nanai_kit.security.service;

import com.nanai_kit.exception.BadRequestException;
import com.nanai_kit.features.usuario.model.RolUsuario;
import com.nanai_kit.features.usuario.model.Usuario;
import com.nanai_kit.features.usuario.repository.UsuarioRepository;
import com.nanai_kit.security.dto.AuthRequest;
import com.nanai_kit.security.dto.AuthResponse;
import com.nanai_kit.security.dto.RegisterRequest;
import com.nanai_kit.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest req) {
        // autentica credenciales
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email(), req.contrasena()));

        // carga usuario para responder con sus datos
        Usuario u = usuarioRepository.findByEmailIgnoreCase(req.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String jwt = jwtService.generateToken(u.getEmail());
        return new AuthResponse(u.getNombre(), u.getEmail(), u.getRol(), jwt);
    }

    public AuthResponse register(RegisterRequest r) {
        if (usuarioRepository.existsByEmailIgnoreCase(r.email())) {
            throw new BadRequestException("El email ya está registrado");
        }
        Usuario nuevo = Usuario.builder()
                .nombre(r.nombre())
                .email(r.email())
                .contrasenaHash(passwordEncoder.encode(r.contrasena()))
                .rol(r.rol() != null ? r.rol() : RolUsuario.USUARIO)
                .telefono(r.telefono())
                .comuna(r.comuna())
                .direccion(r.direccion())
                .activo(true)
                .build();

        usuarioRepository.save(nuevo);

        String jwt = jwtService.generateToken(nuevo.getEmail());
        return new AuthResponse(nuevo.getNombre(), nuevo.getEmail(), nuevo.getRol(), jwt);
    }
}
