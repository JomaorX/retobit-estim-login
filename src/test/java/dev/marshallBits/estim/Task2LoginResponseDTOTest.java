package dev.marshallBits.estim;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task2LoginResponseDTOTest {

    @Test
    @Order(1)
    @DisplayName("TAREA 2: Debe existir el DTO LoginResponseDTO")
    void testLoginResponseDTOExists() {
        try {
            Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 2: Debes crear el DTO 'LoginResponseDTO' en el paquete dev.marshallBits.estim.dto");
        }
    }

    @Test
    @Order(2)
    @DisplayName("TAREA 2: LoginResponseDTO debe tener el campo 'token' de tipo String")
    void testLoginResponseDTOHasTokenField() {
        try {
            Class<?> loginResponseClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");
            Field tokenField = loginResponseClass.getDeclaredField("token");
            assertEquals(String.class, tokenField.getType(),
                    "❌ TAREA 2: El campo 'token' debe ser de tipo String");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 2: Debes crear 'LoginResponseDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 2: El LoginResponseDTO debe tener un campo 'token' de tipo String");
        }
    }

    @Test
    @Order(3)
    @DisplayName("TAREA 2: LoginResponseDTO debe tener el campo 'username' de tipo String")
    void testLoginResponseDTOHasUsernameField() {
        try {
            Class<?> loginResponseClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");
            Field usernameField = loginResponseClass.getDeclaredField("username");
            assertEquals(String.class, usernameField.getType(),
                    "❌ TAREA 2: El campo 'username' debe ser de tipo String");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 2: Debes crear 'LoginResponseDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 2: El LoginResponseDTO debe tener un campo 'username' de tipo String");
        }
    }

    @Test
    @Order(4)
    @DisplayName("TAREA 2: LoginResponseDTO debe tener el campo 'email' de tipo String")
    void testLoginResponseDTOHasEmailField() {
        try {
            Class<?> loginResponseClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");
            Field emailField = loginResponseClass.getDeclaredField("email");
            assertEquals(String.class, emailField.getType(),
                    "❌ TAREA 2: El campo 'email' debe ser de tipo String");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 2: Debes crear 'LoginResponseDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 2: El LoginResponseDTO debe tener un campo 'email' de tipo String");
        }
    }

    @Test
    @Order(5)
    @DisplayName("TAREA 2: LoginResponseDTO debe tener el campo 'id' de tipo Long")
    void testLoginResponseDTOHasIdField() {
        try {
            Class<?> loginResponseClass = Class.forName("dev.marshallBits.estim.dto.LoginResponseDTO");
            Field idField = loginResponseClass.getDeclaredField("id");
            assertTrue(idField.getType() == Long.class || idField.getType() == long.class,
                    "❌ TAREA 2: El campo 'id' debe ser de tipo Long");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 2: Debes crear 'LoginResponseDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 2: El LoginResponseDTO debe tener un campo 'id' de tipo Long");
        }
    }

}
