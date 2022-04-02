package org.elhg.apiservlet.webapp.cookies.services;

import org.elhg.apiservlet.webapp.cookies.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
}
