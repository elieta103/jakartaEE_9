package org.elhg.apiservlet.webapp.jdbcpool.services;

import org.elhg.apiservlet.webapp.jdbcpool.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
