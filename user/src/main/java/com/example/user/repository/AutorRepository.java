package com.example.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByUsername(String username);
    Optional<Autor> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
