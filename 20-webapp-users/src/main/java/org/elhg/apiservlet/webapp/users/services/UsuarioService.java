package org.elhg.apiservlet.webapp.users.services;

import org.elhg.apiservlet.webapp.users.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
