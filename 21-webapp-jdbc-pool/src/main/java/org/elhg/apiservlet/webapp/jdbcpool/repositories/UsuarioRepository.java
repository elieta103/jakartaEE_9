package org.elhg.apiservlet.webapp.jdbcpool.repositories;

import org.elhg.apiservlet.webapp.jdbcpool.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
