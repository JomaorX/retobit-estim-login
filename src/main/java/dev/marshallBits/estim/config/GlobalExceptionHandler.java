package dev.marshallBits.estim.config;

import dev.marshallBits.estim.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

// Gestor global de Excepciones
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Para gestionar las validaciones una vez añadimos el @Valid en los controladores.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>(); // podemos guardar los errores en una arrayList también

        // Hacemos un loop para recorrer todos los resultados de la excepción y añadir los mensajes
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        // Creamos el objeto del errorResponseDTO, el que mandaremos de vuelta
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            "Error de validación",
            HttpStatus.BAD_REQUEST.value(), // Esto nos da un int
            errors
        );
        // El .badRequest() ya genera una nueva instancia. En este caso no es necesaria la palabra "new"
        return ResponseEntity.badRequest().body(errorResponse); // añadimos al body de la respuesta el objeto entero.
    }

}
