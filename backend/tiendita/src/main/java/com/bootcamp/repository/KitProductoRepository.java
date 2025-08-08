package com.bootcamp.repository;

import com.bootcamp.model.KitProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitProductoRepository extends JpaRepository<KitProducto, Long> {
}
