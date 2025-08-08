package com.bootcamp.repository;

import com.bootcamp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

=======
import org.springframework.stereotype.Repository;

@Repository
>>>>>>> rodri-fullstack-main
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
