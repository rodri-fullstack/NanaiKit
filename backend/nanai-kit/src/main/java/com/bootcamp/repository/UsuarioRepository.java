package com.bootcamp.repository;

import com.bootcamp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Búsqueda por nombre de Usuario (contiene, case-insensitive)
    Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    // Obtener todos ordenados por email
    Page<Usuario> findAllByOrderByEmailAsc(Pageable pageable);

    // 🔐 Buscar por email para login
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}

// También podemos usar una consulta @Query para más control
//@Query("SELECT c FROM Usuario c WHERE LOWER(usuario.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
//Page<Usuario> buscarPorNombre(String nombre, Pageable pageable);