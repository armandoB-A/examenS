package com.example.examentr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.examentr.entities.Usuario;
import com.example.examentr.repositories.UsuarioRepository;
import com.example.examentr.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class ExamenTrApplicationTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void getUsers_ShouldReturnListOfUsers() {
        // Arrange
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario());
        usuarios.add(new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> result = usuarioService.getUsers();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void getUserById_WithValidId_ShouldReturnUser() {
        // Arrange
        int userId = 1;
        Usuario usuario = new Usuario();
        usuario.setId(userId);
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

        // Act
        Usuario result = usuarioService.getUserByID(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

}
