package com.example.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.model.Autor;
import com.example.user.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class AutorController {

    @Autowired
    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

 
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody Autor user) {
        try {
        
            if (autorService.existsByEmail(user.getEmail())) {
                return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error: El email ya está en uso!");
            }
            
            if (autorService.existsByUsername(user.getUsername())) {
                return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error: El nombre de usuario ya está en uso!");
            }
            
            Autor newUser = autorService.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el usuario: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateUser(@PathVariable Long id, @RequestBody Autor userData) {
        Optional<Autor> updatedUser = autorService.update(id, userData);
        return updatedUser.map(user -> ResponseEntity.ok(user))
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @GetMapping
    public List<Autor> getAllUsers() {
        return autorService.findAll();
    }

    
    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<Autor>> findByUsername(@PathVariable String username) {
        Optional<Autor> user = autorService.findByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Autor>> findByEmail(@PathVariable String email) {
        Optional<Autor> user = autorService.findByEmail(email);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

 
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(autorService.existsByUsername(username));
    }

   
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(autorService.existsByEmail(email));
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        autorService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
