package dev.marshallBits.estim;

import dev.marshallBits.estim.models.User;
import dev.marshallBits.estim.models.Role;
import dev.marshallBits.estim.repositories.UserRepository;
import dev.marshallBits.estim.services.UserService;
import dev.marshallBits.estim.security.JwtUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task4UserServiceImplementationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private JwtUtil jwtUtil;

    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        // Crear un usuario de prueba con password encriptado
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
    @DisplayName("TAREA 4: UserServiceImpl debe implementar el método authenticateUser")
    void testUserServiceImplHasAuthenticateUserMethod() {
        try {
            Class<?> userServiceImplClass = Class.forName("dev.marshallBits.estim.services.UserServiceImpl");
            Method authenticateUserMethod = null;

            for (Method method : userServiceImplClass.getDeclaredMethods()) {
                if ("authenticateUser".equals(method.getName())) {
                    authenticateUserMethod = method;
                    break;
                }
            }

            assertNotNull(authenticateUserMethod,
                    "❌ TAREA 4: UserServiceImpl debe implementar el método 'authenticateUser'");

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 4: No se encontró la clase UserServiceImpl");
        }
    }

    @Test
    @Order(2)
    @DisplayName("TAREA 4: authenticateUser debe encontrar usuario por email")
    void testAuthenticateUserFindsUserByEmail() {
        try {
            // Verificar que existe un LoginResponseDTO
            Class<?> loginResponseDTOClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");

            Object result = userService.authenticateUser("test@example.com", "Password123");

            assertNotNull(result,
                    "❌ TAREA 4: authenticateUser no debe devolver null para credenciales válidas");

            assertTrue(loginResponseDTOClass.isInstance(result),
                    "❌ TAREA 4: authenticateUser debe devolver una instancia de LoginResponseDTO");

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 4: Primero debes crear el LoginResponseDTO (Tarea 2)");
        } catch (Exception e) {
            fail("❌ TAREA 4: Error al ejecutar authenticateUser: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    @DisplayName("TAREA 4: authenticateUser debe verificar el password correctamente")
    void testAuthenticateUserVerifiesPassword() {
        try {
            // Intentar con password incorrecto
            assertThrows(RuntimeException.class,
                () -> userService.authenticateUser("test@example.com", "WrongPassword"),
                "❌ TAREA 4: authenticateUser debe lanzar una excepción para passwords incorrectos");

        } catch (Exception e) {
            // Si no lanza excepción, verificar que devuelve null
            Object result = userService.authenticateUser("test@example.com", "WrongPassword");
            assertNull(result,
                    "❌ TAREA 4: authenticateUser debe devolver null o lanzar excepción para passwords incorrectos");
        }
    }

    @Test
    @Order(4)
    @DisplayName("TAREA 4: authenticateUser debe generar un token JWT válido")
    void testAuthenticateUserGeneratesValidJWT() {
        try {
            Class<?> loginResponseDTOClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");

            Object result = userService.authenticateUser("test@example.com", "Password123");
            assertNotNull(result, "❌ TAREA 4: authenticateUser no debe devolver null");

            // Usar reflexión para obtener el token
            Method getTokenMethod = loginResponseDTOClass.getMethod("getToken");
            String token = (String) getTokenMethod.invoke(result);

            assertNotNull(token,
                    "❌ TAREA 4: El LoginResponseDTO debe contener un token no nulo");

            assertFalse(token.isEmpty(),
                    "❌ TAREA 4: El token no debe estar vacío");

            // Verificar que el token tiene formato JWT básico (3 partes separadas por puntos)
            String[] tokenParts = token.split("\\.");
            assertEquals(3, tokenParts.length,
                    "❌ TAREA 4: El token debe tener formato JWT válido (header.payload.signature)");

            // Si JwtUtil está disponible, verificar que el token es válido
            if (jwtUtil != null) {
                assertDoesNotThrow(() -> jwtUtil.getUsernameFromToken(token),
                        "❌ TAREA 4: El token generado debe ser válido según JwtUtil");
            }

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 4: Primero debes crear el LoginResponseDTO (Tarea 2)");
        } catch (Exception e) {
            fail("❌ TAREA 4: Error al verificar el token JWT: " + e.getMessage());
        }
    }

    @Test
    @Order(5)
    @DisplayName("TAREA 4: LoginResponseDTO debe contener la información correcta del usuario")
    void testAuthenticateUserReturnsCorrectUserInfo() {
        try {
            Class<?> loginResponseDTOClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");

            Object result = userService.authenticateUser("test@example.com", "Password123");
            assertNotNull(result, "❌ TAREA 4: authenticateUser no debe devolver null");

            // Verificar username
            Method getUsernameMethod = loginResponseDTOClass.getMethod("getUsername");
            String username = (String) getUsernameMethod.invoke(result);
            assertEquals("testuser", username,
                    "❌ TAREA 4: El LoginResponseDTO debe contener el username correcto");

            // Verificar email
            Method getEmailMethod = loginResponseDTOClass.getMethod("getEmail");
            String email = (String) getEmailMethod.invoke(result);
            assertEquals("test@example.com", email,
                    "❌ TAREA 4: El LoginResponseDTO debe contener el email correcto");

            // Verificar id
            Method getIdMethod = loginResponseDTOClass.getMethod("getId");
            Long id = (Long) getIdMethod.invoke(result);
            assertEquals(testUser.getId(), id,
                    "❌ TAREA 4: El LoginResponseDTO debe contener el id correcto del usuario");

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 4: Primero debes crear el LoginResponseDTO (Tarea 2)");
        } catch (Exception e) {
            fail("❌ TAREA 4: Error al verificar la información del usuario: " + e.getMessage());
        }
    }

    @Test
    @Order(6)
    @DisplayName("TAREA 4: authenticateUser debe manejar usuario no encontrado")
    void testAuthenticateUserHandlesUserNotFound() {
        try {
            assertThrows(RuntimeException.class,
                () -> userService.authenticateUser("nonexistent@example.com", "Password123"),
                "❌ TAREA 4: authenticateUser debe lanzar una excepción para usuarios no encontrados");

        } catch (AssertionError e) {
            // Si no lanza excepción, verificar que devuelve null
            Object result = userService.authenticateUser("nonexistent@example.com", "Password123");
            assertNull(result,
                    "❌ TAREA 4: authenticateUser debe devolver null o lanzar excepción para usuarios no encontrados");
        }
    }
}
