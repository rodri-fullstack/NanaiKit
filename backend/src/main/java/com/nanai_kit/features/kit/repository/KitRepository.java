package com.nanai_kit.features.kit.repository;

import com.nanai_kit.features.kit.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {

    Optional<Kit> findByCodigo(String codigo);

}
