package br.com.nutriplanner.user_service.controller;

import br.com.nutriplanner.user_service.dto.UserLoginDTO;
import br.com.nutriplanner.user_service.dto.UserRegistrationDTO;
import br.com.nutriplanner.user_service.dto.UserResponseDTO;
import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") 
public class UserController {

    @Autowired
    private UserService service;


   @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User newUser = service.registerUser(user);
            return ResponseEntity.status(201).body(newUser);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("já está em uso")) {
                return ResponseEntity.status(409).body(null); // Conflict
            } else if (e.getMessage().contains("não pode ser vazio")) {
                return ResponseEntity.badRequest().body(null); // Bad Request
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean sucesso = service.login(user);
        if (sucesso) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas.");
        }
    }

    @PutMapping("/{id}/preferencias")
    public ResponseEntity<User> atualizarPreferencias(@PathVariable Long id, @RequestBody User dadosAtualizacao) {
        return ResponseEntity.ok(service.atualizarPreferencias(id, dadosAtualizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/internal/by-name/{username}")
    public ResponseEntity<User> getInternalUserByName(@PathVariable("username") String username) {
        try {
            User user = service.findUserByName(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/internal/all")
    public ResponseEntity<List<User>> listarTodosUsuarios() {
        return ResponseEntity.ok(service.listarTodosUsuarios());
    }
}