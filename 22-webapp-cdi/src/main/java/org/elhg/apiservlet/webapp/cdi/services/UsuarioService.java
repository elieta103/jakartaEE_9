package org.elhg.apiservlet.webapp.cdi.services;

import org.elhg.apiservlet.webapp.cdi.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(String username, String password);
}
