package com.bootcamp.repository;

import com.bootcamp.model.TestEmocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEmocionalRepository extends JpaRepository<TestEmocional, Long> {
}
