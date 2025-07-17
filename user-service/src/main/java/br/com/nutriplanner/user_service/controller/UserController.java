package br.com.nutriplanner.user_service.controller;

import br.com.nutriplanner.user_service.dto.UserRegistrationDTO;
import br.com.nutriplanner.user_service.dto.UserResponseDTO;
import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") 
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO dto) {
        try {
            UserResponseDTO newUser = service.registerUser(dto);
            return ResponseEntity.status(201).body(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Retornar um corpo nulo ou uma mensagem de erro
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
        // Agora chama o serviço, em vez do repositório direto. Mais limpo!
        try {
            User user = service.findUserByName(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}