package org.elhg.apiservlet.webapp.cookies.services;

import org.elhg.apiservlet.webapp.cookies.models.Producto;


import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }
}
