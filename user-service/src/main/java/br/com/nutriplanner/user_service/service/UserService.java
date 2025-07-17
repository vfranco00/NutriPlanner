package br.com.nutriplanner.user_service.service;

import br.com.nutriplanner.user_service.dto.UserLoginDTO;
import br.com.nutriplanner.user_service.dto.UserRegistrationDTO;
import br.com.nutriplanner.user_service.dto.UserResponseDTO;
import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        if (repository.findByName(dto.name()).isPresent()) {
            throw new RuntimeException("Erro: Nome de usuário já está em uso.");
        }

        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setPassword(dto.password());
        newUser.setPreferences(dto.preferences());

        User savedUser = repository.save(newUser);

        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getPreferences());
    }

    @Transactional(readOnly = true)
    public boolean login(UserLoginDTO dto) {
        return repository.findByName(dto.name())
                .map(user -> user.getPassword().equals(dto.password()))
                .orElse(false);
    }

    public User atualizarPreferencias(Long id, User dadosAtualizacao) {
        User usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Atualiza apenas as preferências
        usuario.setVegano(dadosAtualizacao.isVegano());
        usuario.setVegano(dadosAtualizacao.isVegetariano());
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
}