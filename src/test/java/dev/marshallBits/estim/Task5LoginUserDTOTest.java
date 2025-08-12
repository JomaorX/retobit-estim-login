package dev.marshallBits.estim;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task5LoginUserDTOTest {

    @Test
    @Order(1)
    @DisplayName("TAREA 5: Debe existir el DTO LoginUserDTO")
    void testLoginUserDTOExists() {
        try {
            Class.forName("dev.marshallBits.estim.dto.LoginUserDTO");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 5: Debes crear el DTO 'LoginUserDTO' en el paquete dev.marshallBits.estim.dto");
        }
    }

    @Test
    @Order(2)
    @DisplayName("TAREA 5: LoginUserDTO debe tener el campo 'email' de tipo String")
    void testLoginUserDTOHasEmailField() {
        try {
            Class<?> loginUserClass = Class.forName("dev.marshallBits.estim.dto.LoginUserDTO");
            Field emailField = loginUserClass.getDeclaredField("email");
            assertEquals(String.class, emailField.getType(),
                    "❌ TAREA 5: El campo 'email' debe ser de tipo String");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 5: Debes crear 'LoginUserDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 5: El LoginUserDTO debe tener un campo 'email' de tipo String");
        }
    }

    @Test
    @Order(3)
    @DisplayName("TAREA 5: LoginUserDTO debe tener el campo 'password' de tipo String")
    void testLoginUserDTOHasPasswordField() {
        try {
            Class<?> loginUserClass = Class.forName("dev.marshallBits.estim.dto.LoginUserDTO");
            Field passwordField = loginUserClass.getDeclaredField("password");
            assertEquals(String.class, passwordField.getType(),
                    "❌ TAREA 5: El campo 'password' debe ser de tipo String");
        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 5: Debes crear 'LoginUserDTO' en el paquete dev.marshallBits.estim.dto");
        } catch (NoSuchFieldException e) {
            fail("❌ TAREA 5: El LoginUserDTO debe tener un campo 'password' de tipo String");
        }
    }

    @Test
    @Order(4)
    @DisplayName("TAREA 5: LoginUserDTO no debe tener campos adicionales innecesarios")
    void testLoginUserDTOOnlyHasRequiredFields() {
        try {
            Class<?> loginUserClass = Class.forName("dev.marshallBits.estim.dto.LoginUserDTO");
            Field[] fields = loginUserClass.getDeclaredFields();

            // Contar solo los campos que no son generados por el compilador
            int userDefinedFields = 0;
            for (Field field : fields) {
                if (!field.getName().startsWith("$") && !field.isSynthetic()) {
                    userDefinedFields++;
                }
            }

            assertEquals(2, userDefinedFields,
                    "❌ TAREA 5: LoginUserDTO debe tener exactamente 2 campos: 'email' y 'password'");

        } catch (ClassNotFoundException e) {
            fail("❌ TAREA 5: Debes crear 'LoginUserDTO' en el paquete dev.marshallBits.estim.dto");
        }
    }
}
