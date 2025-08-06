package br.com.nutriplanner.user_service.service;

import br.com.nutriplanner.user_service.model.User;
import br.com.nutriplanner.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("victor");
        mockUser.setPassword("1234");
        mockUser.setVegano(false);
        mockUser.setVegetariano(false);
        mockUser.setSemLactose(false);
        mockUser.setSemGluten(false);
    }

    @Test
    public void testRegisterUser_Success() {
        when(userRepository.findByName("victor")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User savedUser = userService.registerUser(mockUser);

        assertNotNull(savedUser);
        assertEquals("victor", savedUser.getName());
        verify(userRepository).save(mockUser);
    }

    @Test
    public void testRegisterUser_DuplicateName() {
        when(userRepository.findByName("victor")).thenReturn(Optional.of(mockUser));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(mockUser);
        });

        assertTrue(exception.getMessage().contains("já está em uso"));
    }

    @Test
    public void testRegisterUser_EmptyName() {
        mockUser.setName("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(mockUser);
        });

        assertTrue(exception.getMessage().contains("Nome não pode ser vazio"));
    }

    @Test
    public void testRegisterUser_EmptyPassword() {
        mockUser.setPassword("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(mockUser);
        });

        assertTrue(exception.getMessage().contains("Senha não pode ser vazia"));
    }

    @Test
    public void testLogin_Success() {
        when(userRepository.findByName("victor")).thenReturn(Optional.of(mockUser));

        boolean result = userService.login(mockUser);

        assertTrue(result);
    }

    @Test
    public void testLogin_Failure() {
        when(userRepository.findByName("victor")).thenReturn(Optional.empty());

        boolean result = userService.login(mockUser);

        assertFalse(result);
    }

    @Test
    public void testAtualizarPreferencias_Success() {
        User newData = new User();
        newData.setVegano(true);
        newData.setVegetariano(true);
        newData.setSemLactose(true);
        newData.setSemGluten(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User updated = userService.atualizarPreferencias(1L, newData);

        assertTrue(updated.isVegano());
        assertTrue(updated.isVegetariano());
        assertTrue(updated.isSemLactose());
        assertTrue(updated.isSemGluten());
    }

    @Test
    public void testDeletarUsuario_Success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deletarUsuario(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    public void testDeletarUsuario_NotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deletarUsuario(1L);
        });

        assertTrue(exception.getMessage().contains("Usuário não encontrado"));
    }

    @Test
    public void testFindUserByName_Success() {
        when(userRepository.findByName("victor")).thenReturn(Optional.of(mockUser));

        User user = userService.findUserByName("victor");

        assertEquals("victor", user.getName());
    }

    @Test
    public void testFindUserByName_NotFound() {
        when(userRepository.findByName("victor")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.findUserByName("victor");
        });

        assertTrue(exception.getMessage().contains("Usuário não encontrado"));
    }

    @Test
    public void testListarTodosUsuarios() {
        List<User> users = Arrays.asList(mockUser);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.listarTodosUsuarios();

        assertEquals(1, result.size());
        assertEquals("victor", result.get(0).getName());
    }
}
