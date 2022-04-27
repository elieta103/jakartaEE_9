package org.elhg.apiservlet.webapp.cdi.services;

import org.elhg.apiservlet.webapp.cdi.models.Categoria;
import org.elhg.apiservlet.webapp.cdi.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
