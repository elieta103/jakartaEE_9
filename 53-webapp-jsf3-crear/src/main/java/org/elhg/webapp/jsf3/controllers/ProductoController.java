package org.elhg.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.elhg.webapp.jsf3.entities.Producto;
import org.elhg.webapp.jsf3.services.ProductoService;

import java.util.List;

@Model
public class ProductoController {

    private Producto producto;

    @Inject
    private ProductoService service;

    @Produces
    @Model
    public String titulo() {
        return "Hola mundo JavaServer Faces 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")
    public List<Producto> findAll() {
//        return Arrays.asList(new Producto("peras"), new Producto("manzanas"), new Producto("mandarinas"));
        return service.listar();
    }

    // Crea en obj y lo escribe en CDI, en la vista usamos : producto
    @Produces
    @Model
    public Producto producto() {
        this.producto = new Producto();
        return producto;
    }

    public String guardar() {
        //index.xhtml?faces-redirect=true  Cambia el request lo reenvia en uno nuevo
        //index.xhtml No Cambia el request lo reenvia en el mismo es como un forward

        System.out.println(producto);
        service.guardar(producto);
        return "index.xhtml?faces-redirect=true";
    }
}
