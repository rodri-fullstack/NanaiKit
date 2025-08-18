package com.bootcamp.repository;

import com.bootcamp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //JpaRepository nos da CRUD completo automáticamente.
}
