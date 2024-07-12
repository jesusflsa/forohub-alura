package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        @NotNull(message = "El tópico necesita un usuario")
        @JsonAlias("id_usuario")
        Long idUsuario,

        @NotBlank(message = "Necesitas asignar un título")
        String titulo,

        @NotBlank(message = "Agrega un mensaje a tu tópico")
        String mensaje,

        @NotBlank(message = "Ingresa un nombre de curso")
        @JsonAlias("curso")
        String curso
) {
}
