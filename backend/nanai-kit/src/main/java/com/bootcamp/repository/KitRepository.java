package com.bootcamp.repository;

import com.bootcamp.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {
}
