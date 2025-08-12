package dev.marshallBits.estim;

import dev.marshallBits.estim.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task3UserServiceInterfaceTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("TAREA 3: UserService debe tener el método authenticateUser")
    void testUserServiceHasAuthenticateUserMethod() {
        try {
            Class<?> userServiceClass = Class.forName("dev.marshallBits.estim.services.UserService");
            Method[] methods = userServiceClass.getDeclaredMethods();

            boolean hasAuthenticateUserMethod = false;
            for (Method method : methods) {
                if ("authenticateUser".equals(method.getName())) {
                    hasAuthenticateUserMethod = true;
                    break;
                }
            }

            assertTrue(hasAuthenticateUserMethod,
                    "❌ TAREA 3: La interfaz UserService debe tener un método llamado 'authenticateUser'");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 3: No se encontró la interfaz UserService");
        }
    }

    @Test
    @Order(2)
    @DisplayName("TAREA 3: El método authenticateUser debe recibir email y password")
    void testAuthenticateUserMethodSignature() {
        try {
            Class<?> userServiceClass = Class.forName("dev.marshallBits.estim.services.UserService");
            Method authenticateUserMethod = null;

            for (Method method : userServiceClass.getDeclaredMethods()) {
                if ("authenticateUser".equals(method.getName())) {
                    authenticateUserMethod = method;
                    break;
                }
            }

            assertNotNull(authenticateUserMethod,
                    "❌ TAREA 3: La interfaz UserService debe tener un método llamado 'authenticateUser'");

            Class<?>[] parameterTypes = authenticateUserMethod.getParameterTypes();
            assertEquals(2, parameterTypes.length,
                    "❌ TAREA 3: El método authenticateUser debe recibir exactamente 2 parámetros (email y password)");

            assertEquals(String.class, parameterTypes[0],
                    "❌ TAREA 3: El primer parámetro del método authenticateUser debe ser String (email)");

            assertEquals(String.class, parameterTypes[1],
                    "❌ TAREA 3: El segundo parámetro del método authenticateUser debe ser String (password)");

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 3: No se encontró la interfaz UserService");
        }
    }

    @Test
    @Order(3)
    @DisplayName("TAREA 3: El método authenticateUser debe devolver LoginResponseDTO")
    void testAuthenticateUserReturnType() {
        try {
            Class<?> userServiceClass = Class.forName("dev.marshallBits.estim.services.UserService");
            Class<?> loginResponseDTOClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");

            Method authenticateUserMethod = null;
            for (Method method : userServiceClass.getDeclaredMethods()) {
                if ("authenticateUser".equals(method.getName())) {
                    authenticateUserMethod = method;
                    break;
                }
            }

            assertNotNull(authenticateUserMethod,
                    "❌ TAREA 3: La interfaz UserService debe tener un método llamado 'authenticateUser'");

            assertEquals(loginResponseDTOClass, authenticateUserMethod.getReturnType(),
                    "❌ TAREA 3: El método authenticateUser debe devolver un objeto LoginResponseDTO");

        } catch (ClassNotFoundException e) {
            if (e.getMessage().contains("LoginResponseDTO")) {
                fail("❌ TAREA 3: Primero debes crear el LoginResponseDTO (Tarea 2)");
            } else {
                fail("❌ TAREA 3: No se encontró la interfaz UserService");
            }
        }
    }
}
