package com.bootcamp.security.repository;

import com.bootcamp.security.model.UsuarioSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorySec extends JpaRepository<UsuarioSec, Long> {

    Optional<UsuarioSec> findByEmail(String email);

    boolean existsByEmail(String email);
}
