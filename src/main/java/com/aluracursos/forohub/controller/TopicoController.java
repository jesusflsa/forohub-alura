package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> obtenerTopicos(@PageableDefault(sort = "fechaDeCreacion", direction = Sort.Direction.ASC) Pageable page) {
        var topicos = topicoService.obtenerTopicos(page);
        return ResponseEntity.ok(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosCrearTopico datos) {
        var topico = topicoService.crear(datos);
        URI url = UriComponentsBuilder.fromPath("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuestaTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopicoPorId(@PathVariable Long id) {
        DatosDetalleTopico topico = topicoService.obtenerPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizarTopicoPorId(@PathVariable Long id, @RequestBody DatosActualizarTopico datos) {
        var topico = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopicoPorId(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.ok().build();
    }

}
