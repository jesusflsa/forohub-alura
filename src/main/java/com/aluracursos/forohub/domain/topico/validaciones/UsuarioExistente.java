package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosCrearTopico;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioExistente implements ValidacionCrearTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void validar(DatosCrearTopico datos) {
        var usuarioExistente = usuarioRepository.existsById(datos.idUsuario());
        if (!usuarioExistente)
            throw new ValidationException("Ingresa un id de usuario válido");
    }
}
