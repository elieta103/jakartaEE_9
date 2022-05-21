package org.elhg.webapp.ear.ejb.repositories;

import org.elhg.webapp.ear.ejb.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> listar();
}
