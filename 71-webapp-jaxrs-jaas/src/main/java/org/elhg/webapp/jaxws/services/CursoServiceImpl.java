package org.elhg.webapp.jaxws.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.elhg.webapp.jaxws.models.Curso;
import org.elhg.webapp.jaxws.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoRepository repository;

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Curso guardar(Curso curso) {
        return repository.guardar(curso);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @Override
    public Optional<Curso> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
