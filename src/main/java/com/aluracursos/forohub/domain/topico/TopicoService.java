package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.topico.validaciones.ValidacionCrearTopico;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidacionCrearTopico> validadoresCrear;

    public Topico crear(DatosCrearTopico datos) {
        validadoresCrear.forEach(v -> v.validar(datos));
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var topico = new Topico(datos, usuario);
        return topicoRepository.save(topico);
    }

    public Page<DatosRespuestaTopico> obtenerTopicos(Pageable page) {
        return topicoRepository.findAllByStatusTrue(page)
                .map(DatosRespuestaTopico::new);
    }

    public DatosDetalleTopico obtenerPorId(Long id) {
        var topicoOptional = topicoRepository.findByIdAndStatusTrue(id);
        if (topicoOptional.isEmpty())
            throw new EntityNotFoundException("El tópico solicitado no existe");

        var topico = topicoOptional.get();
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizarTopico(Long id, DatosActualizarTopico datos) {
        var topicoOptional = topicoRepository.findByIdAndStatusTrue(id);
        if (topicoOptional.isEmpty())
            throw new EntityNotFoundException("El tópico no existe");

        var topicoRepetido = topicoRepository.buscarPorNoIdYDatos(id, datos.titulo(), datos.mensaje());
        if (topicoRepetido)
            throw new ValidationException("Ya existe un topico con este titulo y mensaje");

        var topico = topicoOptional.get();
        topico.actualizarDatos(datos);

        return new DatosDetalleTopico(topico);
    }

    public void eliminarTopico(Long id) {
        var topicoOptional = topicoRepository.findByIdAndStatusTrue(id);
        if (topicoOptional.isEmpty())
            throw new EntityNotFoundException("El tópico no existe");

        var topico = topicoOptional.get();
        topico.eliminar();
    }

}
