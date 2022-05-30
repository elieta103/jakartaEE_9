package org.elhg.webapp.jaxws.repositories;

import org.elhg.webapp.jaxws.models.Curso;

import java.util.List;

public interface CursoRepository {
    List<Curso> listar();
    Curso guardar(Curso curso);
}
