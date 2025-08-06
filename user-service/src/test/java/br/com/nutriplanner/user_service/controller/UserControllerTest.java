package br.com.nutriplanner.user_service.controller;

import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User mockUser;

    @BeforeEach
    public void setup() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("victor");
        mockUser.setPassword("1234");
    }

    @Test
    public void testRegisterUser() throws Exception {
        Mockito.when(userService.registerUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/usuarios/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("victor"));
    }

    @Test
    public void testLoginUser() throws Exception {
        Mockito.when(userService.login(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/usuarios/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login bem-sucedido"));
    }

    @Test
    public void testAtualizarPreferencias() throws Exception {
        Mockito.when(userService.atualizarPreferencias(eq(1L), any(User.class))).thenReturn(mockUser);

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("victor"));
    }

    @Test
    public void testDeletarUsuario() throws Exception {
        Mockito.doNothing().when(userService).deletarUsuario(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testBuscarUsuarioPorNome() throws Exception {
        Mockito.when(userService.findUserByName("victor")).thenReturn(mockUser);

        mockMvc.perform(get("/usuarios/buscar/victor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("victor"));
    }

    @Test
    public void testListarTodosUsuarios() throws Exception {
        Mockito.when(userService.listarTodosUsuarios()).thenReturn(Collections.singletonList(mockUser));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("victor"));
    }
}
