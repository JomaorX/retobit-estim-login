package dev.marshallBits.estim.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserDTO {

    @NotBlank(message = "Debe introductir el nombre de usuario")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "Debe introductir la contraseña")
    private String password;
}
