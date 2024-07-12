package com.aluracursos.forohub.infra.errores;

import org.springframework.validation.FieldError;

public record ErrorFieldMessage(
        String field,
        String message
) {
    public ErrorFieldMessage(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
