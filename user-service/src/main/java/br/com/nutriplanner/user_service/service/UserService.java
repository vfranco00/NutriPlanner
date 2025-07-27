package br.com.nutriplanner.user_service.service;

//import br.com.nutriplanner.user_service.dto.UserLoginDTO;
//import br.com.nutriplanner.user_service.dto.UserRegistrationDTO;
//import br.com.nutriplanner.user_service.dto.UserResponseDTO;
import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User registerUser(User user) {
        if (repository.findByName(user.getName()).isPresent()) {
            throw new RuntimeException("Erro: Nome de usuário já está em uso.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new RuntimeException("Erro: Nome não pode ser vazio.");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new RuntimeException("Erro: Senha não pode ser vazia.");
        }
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean login(User user) {
        return repository.findByName(user.getName())
                .map(u -> u.getPassword().equals(user.getPassword()))
                .orElse(false);
    }

    public User atualizarPreferencias(Long id, User dadosAtualizacao) {
        User usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setVegano(dadosAtualizacao.isVegano());
        usuario.setVegetariano(dadosAtualizacao.isVegetariano());
        usuario.setSemLactose(dadosAtualizacao.isSemLactose());
        usuario.setSemGluten(dadosAtualizacao.isSemGluten());

        return repository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        repository.deleteById(id);
    }

    public User findUserByName(String username) {
        return repository.findByName(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));
    }

    public List<User> listarTodosUsuarios() {
        return repository.findAll();
    }
}