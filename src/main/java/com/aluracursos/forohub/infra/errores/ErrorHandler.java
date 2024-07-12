package com.aluracursos.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> erroresValidacion(ValidationException exception) {
        return ResponseEntity.badRequest().body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorFieldMessage>> error418(MethodArgumentNotValidException exception) {
//      Im a teapot :D
        var errores = exception.getFieldErrors();
        return ResponseEntity.status(418).body(errores.stream().map(ErrorFieldMessage::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> error404() {
        return ResponseEntity.notFound().build();
    }


}
