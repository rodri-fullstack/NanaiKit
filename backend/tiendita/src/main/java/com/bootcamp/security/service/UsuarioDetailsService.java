package com.bootcamp.security.service;

import com.bootcamp.security.model.UsuarioSec;
import java.util.Optional;

public interface UsuarioDetailsService {
    Optional<UsuarioSec> findByEmail(String email);
}
