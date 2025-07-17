package br.com.nutriplanner.auth_service.service;

import br.com.nutriplanner.auth_service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = "http://servico-usuarios/api/usuarios/internal/by-name/" + username;

        try {
            UserDTO userDTO = restTemplate.getForObject(url, UserDTO.class);

            if (userDTO == null) {
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }

            return new User(userDTO.nome(), userDTO.senha(), new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("Erro ao buscar usuário: " + username, e);
        }
    }
}