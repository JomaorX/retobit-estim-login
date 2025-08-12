package dev.marshallBits.estim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//Este DTO lo usaremos para manejar respuestas que contengan errores.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String message; // El mensaje general del error
    private int status; // El código del status (400, 401...)
    private LocalDateTime timestamp; // la hora a la que se ha producido el error
    private List<String> errors; // Listado de los mensajes de error específicos. (Ej: El password debe contener mínimo 6 caracteres)

    public ErrorResponseDTO(String message, int status, List<String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.timestamp = LocalDateTime.now(); // Así añadimos la fecha actual
    }
}
