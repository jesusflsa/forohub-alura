package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosActualizarTopico;
import com.aluracursos.forohub.domain.topico.DatosCrearTopico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatosUnicos implements ValidacionCrearTopico {

    @Autowired
    private TopicoRepository repository;

    @Override
    public void validar(DatosCrearTopico datos) {
        var tituloExistente = repository.existsByTituloIgnoreCaseAndMensajeIgnoreCase(datos.titulo().trim(), datos.mensaje().trim());

        if (tituloExistente)
            throw new ValidationException("Asigna otro titulo o mensaje a tu tópico");
    }
}
