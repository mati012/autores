package com.example.user.service;

import java.util.List;
import java.util.Optional;

import com.example.user.model.Autor;

public interface AutorService {
    List<Autor> findAll();
    Autor create(Autor user);
    Optional<Autor> update(Long id, Autor userData);
    void deleteUser(Long id);
    Optional<Autor> findByUsername(String username);
    Optional<Autor> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
