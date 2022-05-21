package org.elhg.webapp.ejb.service;

import jakarta.ejb.Stateless;
import org.elhg.webapp.ejb.models.Producto;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ServiceEjb implements ServiceEjbRemote {

    private int contador;

    public String saludar(String nombre) {
        System.out.println("imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en metodo saludar " + contador);
        return "Hola que tal " + nombre;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("peras"));
        productos.add(new Producto("manzanas"));
        productos.add(new Producto("naranjas"));
        return productos;
    }

    @Override
    public Producto crear(Producto producto) {
        System.out.println("guardando producto ..." + producto);
        Producto p = new Producto();
        p.setNombre(producto.getNombre());
        return p;
    }
}
