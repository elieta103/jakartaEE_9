package org.elhg.apiservlet.webapp.users.repositories;

import org.elhg.apiservlet.webapp.users.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
