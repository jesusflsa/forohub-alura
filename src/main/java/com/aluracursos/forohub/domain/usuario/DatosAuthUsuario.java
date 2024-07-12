package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAuthUsuario(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}
