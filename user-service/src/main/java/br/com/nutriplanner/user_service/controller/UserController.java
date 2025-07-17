package br.com.nutriplanner.user_service.controller;

import br.com.nutriplanner.user_service.dto.UserLoginDTO;
import br.com.nutriplanner.user_service.dto.UserRegistrationDTO;
import br.com.nutriplanner.user_service.dto.UserResponseDTO;
import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register") // Endpoint: POST http://localhost:8081/users/register
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO dto) {
        try {
            UserResponseDTO newUser = service.registerUser(dto);
            // Retorna 201 Created com o usuário criado no corpo da resposta
            return ResponseEntity.status(201).body(newUser);
        } catch (RuntimeException e) {
            // Retorna 400 Bad Request se o usuário já existir
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login") // Endpoint: POST http://localhost:8081/users/login
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        boolean isAuthenticated = service.login(dto);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        // Retorna 401 Unauthorized se as credenciais estiverem incorretas
        return ResponseEntity.status(401).body("Credenciais inválidas.");
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
}