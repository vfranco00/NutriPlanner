package br.com.nutriplanner.auth_service.service;

import br.com.nutriplanner.auth_service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class CustomUserDetailsServiceTests {

    @Test
    void deveRetornarUsuarioQuandoEncontrado() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CustomUserDetailsService service = new CustomUserDetailsService();
        service.getClass().getDeclaredFields()[0].setAccessible(true); // gambiarra para injetar

        UserDTO mockUser = new UserDTO("joao", "senha123");
        Mockito.when(restTemplateMock.getForObject(
                "http://servico-usuarios/api/usuarios/internal/by-name/joao", UserDTO.class))
                .thenReturn(mockUser);

        try {
            var field = service.getClass().getDeclaredField("restTemplate");
            field.setAccessible(true);
            field.set(service, restTemplateMock);
        } catch (Exception e) {
            fail("Erro ao injetar dependência");
        }

        UserDetails userDetails = service.loadUserByUsername("joao");

        assertEquals("joao", userDetails.getUsername());
        assertEquals("senha123", userDetails.getPassword());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        CustomUserDetailsService service = new CustomUserDetailsService();

        Mockito.when(restTemplateMock.getForObject(
                "http://servico-usuarios/api/usuarios/internal/by-name/inexistente", UserDTO.class))
                .thenReturn(null);

        try {
            var field = service.getClass().getDeclaredField("restTemplate");
            field.setAccessible(true);
            field.set(service, restTemplateMock);
        } catch (Exception e) {
            fail("Erro ao injetar dependência");
        }

        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("inexistente");
        });
    }
}
