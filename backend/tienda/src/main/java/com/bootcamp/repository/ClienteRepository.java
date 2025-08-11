package com.bootcamp.repository;

import com.bootcamp.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Búsqueda por nombre (contiene, case-insensitive)
    Page<Cliente> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    // También podemos usar una consulta @Query para más control
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Cliente> buscarPorNombre(String nombre, Pageable pageable);

    // Obtener todos ordenados por email
    Page<Cliente> findAllByOrderByEmailAsc(Pageable pageable);

    // 🔐 Buscar por email para login
    Optional<Cliente> findByEmail(String email);
}
