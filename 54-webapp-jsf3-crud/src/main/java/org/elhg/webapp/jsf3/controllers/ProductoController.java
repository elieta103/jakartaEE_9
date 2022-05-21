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

    private Long id;  // Para edicion y Borrado

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

    @Produces
    @Model
    //Se anota para que este disponible en la vista
    public Producto producto() {
        this.producto = new Producto();
        //Edicion
        if (id != null && id > 0) {
            service.porId(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return producto;
    }

    public String editar(Long id){
        //Setea el id para que en el metodo producto() traiga de la BD el elemento a editar
        this.id = id;
        return "form.xhtml";
    }

    public String guardar() {
        System.out.println(producto);
        service.guardar(producto);
        return "index.xhtml?faces-redirect=true";
    }

    public String eliminar(Long id){
        service.eliminar(id);
        return "index.xhtml?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
