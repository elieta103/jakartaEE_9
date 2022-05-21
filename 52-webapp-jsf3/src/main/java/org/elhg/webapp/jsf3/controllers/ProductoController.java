package org.elhg.webapp.jsf3.controllers;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.elhg.webapp.jsf3.entities.Producto;
import org.elhg.webapp.jsf3.services.ProductoService;

import java.util.List;


@Model // @Model = @RequestScope + @Named
public class ProductoController {

    @Inject
    private ProductoService service;

    @Produces
    @Model  // @Model = @RequestScope + @Named
    public String titulo() {
        return "Hola mundo JavaServer Face 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")  // @Model = @RequestScope + @Named
    public List<Producto> findAll() {
        return service.listar();
    }
}
