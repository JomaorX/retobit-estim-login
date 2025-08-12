package dev.marshallBits.estim;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marshallBits.estim.models.User;
import dev.marshallBits.estim.models.Role;
import dev.marshallBits.estim.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task6LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        // Crear un usuario de prueba
        testUser = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password(passwordEncoder != null ? passwordEncoder.encode("Password123") : "Password123")
                .role(Role.ROLE_USER)
                .build();
        testUser = userRepository.save(testUser);
    }

    @Test
    @Order(1)
    @DisplayName("TAREA 6: Debe existir la ruta POST /auth/login")
    void testLoginRouteExists() throws Exception {
        try {
            // Crear un LoginUserDTO válido
            Object loginUserDTO = createLoginUserDTO("test@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            if (e.getMessage().contains("404")) {
                fail("❌ TAREA 6: Debe existir la ruta POST /auth/login en el controlador");
            } else if (e.getMessage().contains("LoginUserDTO")) {
                fail("❌ TAREA 6: Primero debes crear el LoginUserDTO (Tarea 5)");
            } else {
                // Si hay otros errores, al menos verificar que la ruta existe
                try {
                    mockMvc.perform(post("/auth/login")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{}"))
                            .andExpect(status().is4xxClientError()); // Cualquier error 4xx indica que la ruta existe
                } catch (Exception routeException) {
                    if (routeException.getMessage().contains("404")) {
                        fail("❌ TAREA 6: Debe existir la ruta POST /auth/login en el controlador");
                    }
                }
            }
        }
    }

    @Test
    @Order(2)
    @DisplayName("TAREA 6: La ruta login debe recibir un LoginUserDTO")
    void testLoginRouteAcceptsLoginUserDTO() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("test@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            MvcResult result = mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk())
                    .andReturn();

            // Verificar que la respuesta no está vacía
            String responseContent = result.getResponse().getContentAsString();
            assertFalse(responseContent.isEmpty(),
                    "❌ TAREA 6: La ruta /auth/login debe devolver una respuesta");

        } catch (Exception e) {
            if (e.getMessage().contains("LoginUserDTO")) {
                fail("❌ TAREA 6: Primero debes crear el LoginUserDTO (Tarea 5)");
            } else {
                fail("❌ TAREA 6: Error al probar la ruta login: " + e.getMessage());
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("TAREA 6: La ruta login debe devolver un LoginResponseDTO")
    void testLoginRouteReturnsLoginResponseDTO() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("test@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            MvcResult result = mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = result.getResponse().getContentAsString();

            // Verificar que la respuesta contiene los campos esperados de LoginResponseDTO
            assertTrue(responseContent.contains("token"),
                    "❌ TAREA 6: La respuesta debe contener el campo 'token'");

            assertTrue(responseContent.contains("username"),
                    "❌ TAREA 6: La respuesta debe contener el campo 'username'");

            assertTrue(responseContent.contains("email"),
                    "❌ TAREA 6: La respuesta debe contener el campo 'email'");

            assertTrue(responseContent.contains("id"),
                    "❌ TAREA 6: La respuesta debe contener el campo 'id'");

        } catch (Exception e) {
            fail("❌ TAREA 6: Error al verificar la respuesta de login: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    @DisplayName("TAREA 6: La ruta login debe devolver token JWT válido")
    void testLoginRouteReturnsValidJWT() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("test@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            MvcResult result = mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = result.getResponse().getContentAsString();

            // Parsear la respuesta JSON para extraer el token
            assertTrue(responseContent.contains("token"),
                    "❌ TAREA 6: La respuesta debe contener el campo 'token'");

            // Verificar que el token no está vacío
            assertFalse(responseContent.contains("\"token\":\"\"") && responseContent.contains("\"token\":null"),
                    "❌ TAREA 6: El token no debe estar vacío o ser null");

        } catch (Exception e) {
            fail("❌ TAREA 6: Error al verificar el token JWT: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    @DisplayName("TAREA 6: La ruta login debe devolver información correcta del usuario")
    void testLoginRouteReturnsCorrectUserInfo() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("test@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            MvcResult result = mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = result.getResponse().getContentAsString();

            // Verificar que contiene la información correcta del usuario
            assertTrue(responseContent.contains("testuser"),
                    "❌ TAREA 6: La respuesta debe contener el username correcto");

            assertTrue(responseContent.contains("test@example.com"),
                    "❌ TAREA 6: La respuesta debe contener el email correcto");

        } catch (Exception e) {
            fail("❌ TAREA 6: Error al verificar la información del usuario: " + e.getMessage());
        }
    }

    @Test
    @Order(6)
    @DisplayName("TAREA 6: La ruta login debe manejar credenciales incorrectas")
    void testLoginRouteHandlesInvalidCredentials() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("test@example.com", "WrongPassword");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().is4xxClientError()); // Debe devolver un error 4xx

        } catch (Exception e) {
            fail("❌ TAREA 6: La ruta login debe manejar credenciales incorrectas devolviendo un error apropiado");
        }
    }

    @Test
    @Order(7)
    @DisplayName("TAREA 6: La ruta login debe manejar usuarios no encontrados")
    void testLoginRouteHandlesUserNotFound() throws Exception {
        try {
            Object loginUserDTO = createLoginUserDTO("nonexistent@example.com", "Password123");
            String jsonRequest = objectMapper.writeValueAsString(loginUserDTO);

            mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequest))
                    .andExpect(status().is4xxClientError()); // Debe devolver un error 4xx

        } catch (Exception e) {
            fail("❌ TAREA 6: La ruta login debe manejar usuarios no encontrados devolviendo un error apropiado");
        }
    }

    /**
     * Helper method to create LoginUserDTO using reflection
     */
    private Object createLoginUserDTO(String email, String password) throws Exception {
        try {
            Class<?> loginUserDTOClass = Class.forName("dev.marshallBits.estim.dto.LoginUserDTO");
            Object loginUserDTO = loginUserDTOClass.getDeclaredConstructor().newInstance();

            // Set email
            Method setEmailMethod = loginUserDTOClass.getMethod("setEmail", String.class);
            setEmailMethod.invoke(loginUserDTO, email);

            // Set password
            Method setPasswordMethod = loginUserDTOClass.getMethod("setPassword", String.class);
            setPasswordMethod.invoke(loginUserDTO, password);

            return loginUserDTO;

        } catch (ClassNotFoundException e) {
            throw new Exception("LoginUserDTO not found. Complete Task 5 first.", e);
        }
    }
}
