package org.elhg.webapp.ejb.service;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import org.elhg.webapp.ejb.models.Producto;

import java.util.ArrayList;
import java.util.List;

@DeclareRoles({"ADMIN", "USER"})
@Stateful
public class ServiceEjb implements ServiceEjbRemote {

    private int contador;

    @RolesAllowed({"USER", "ADMIN"})
    public String saludar(String nombre) {
        System.out.println("imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en metodo saludar " + contador);
        return "Hola que tal " + nombre;
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("peras"));
        productos.add(new Producto("manzanas"));
        productos.add(new Producto("naranjas"));
        return productos;
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Producto crear(Producto producto) {
        System.out.println("guardando producto ..." + producto);
        Producto p = new Producto();
        p.setNombre(producto.getNombre());
        return p;
    }
}
