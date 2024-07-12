package com.aluracursos.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloIgnoreCaseAndMensajeIgnoreCase(String titulo, String mensaje);

    Page<Topico> findAllByStatusTrue(Pageable page);

    @Query("""
        SELECT count(t) > 0 FROM Topico t
        WHERE t.id != :id AND
        t.titulo = :titulo AND
        t.mensaje = :mensaje
        """)
    boolean buscarPorNoIdYDatos(Long id, String titulo, String mensaje);

    Optional<Topico> findByIdAndStatusTrue(Long id);
}
