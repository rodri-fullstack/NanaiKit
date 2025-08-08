package com.bootcamp.repository;


import com.bootcamp.model.PedidoKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoKitRepository extends JpaRepository<PedidoKit, Long> {
}
