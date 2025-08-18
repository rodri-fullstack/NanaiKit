package com.bootcamp.controller;

import com.bootcamp.model.Usuario;
import com.bootcamp.dto.UsuarioRequest;
import com.bootcamp.dto.UsuarioResponse;
import com.bootcamp.repository.UsuarioRepository;
import com.bootcamp.security.dto.RegisterRequest;
import com.bootcamp.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping("/registrar")
    public boolean registrarUsuario(@RequestBody RegisterRequest request) {
        return usuarioService.registrarUsuario(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest cliente) {
        return ResponseEntity.ok(usuarioService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    // FILTROS Y PAGINACION
    @GetMapping("/buscar")
    public ResponseEntity<Page<Usuario>> buscar(
            @RequestParam(defaultValue = "") String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nombre") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Usuario> resultados = clienteRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/ordenar")
    public ResponseEntity<Page<Usuario>> ordenar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> clientes = clienteRepository.findAllByOrderByEmailAsc(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<Usuario>> paginar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clienteRepository.findAll(pageable));
    }

    @PostMapping("/login")    public ResponseEntity<?> login(@RequestBody UsuarioRequest loginRequest) {
        Usuario usuario = clienteRepository.findByEmail(loginRequest.email())
                .orElse(null); // evitar excepción si no lo encuentra

        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        if (!usuario.getPassword().equals(loginRequest.password())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        UsuarioResponse response = UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .direccion(usuario.getDireccion())
                .telefono(usuario.getTelefono())
                .edad(usuario.getEdad())
                .genero(usuario.getGenero())
                .tipoUsuario(usuario.getTipo_usuario())
                .activo(usuario.getActivo())
                .build(); // ✅ esto convierte el builder en un objeto real
        return ResponseEntity.ok(response);
    }
}
// Recomendación: estos endpoints son útiles para listar muchos registros
// y combinan bien con frontends modernos que permiten buscar, filtrar, ordenar, paginar.
