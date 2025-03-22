package com.example.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.user.model.Autor;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final RoleRepository roleRepository;
    

    @Autowired
    public AutorServiceImpl(AutorRepository userRepository, RoleRepository roleRepository) {
        this.autorRepository = userRepository;
        this.roleRepository = roleRepository;
       
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Autor create(Autor user) {
        return autorRepository.save(user);
    }

    @Override
    public Optional<Autor> update(Long id, Autor userData) {
        return autorRepository.findById(id)
                .map(existingUser -> {
                    if (userData.getName() != null) {
                        existingUser.setName(userData.getName());
                    }
                    if (userData.getEmail() != null) {
                        existingUser.setEmail(userData.getEmail());
                    }
                    
                    
                    if (userData.getPassword() != null) {
                        existingUser.setPassword(userData.getPassword());
                    }
                    if (userData.getRole() != null) {
                        existingUser.setRole(userData.getRole());
                    }
                    return autorRepository.save(existingUser);
                });
    }

    @Override
    public Optional<Autor> findByUsername(String username) {
        return autorRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return autorRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return autorRepository.existsByEmail(email);
    }

    @Override
    public Optional<Autor> findByEmail(String email) {
        return autorRepository.findByEmail(email);
    }
}
