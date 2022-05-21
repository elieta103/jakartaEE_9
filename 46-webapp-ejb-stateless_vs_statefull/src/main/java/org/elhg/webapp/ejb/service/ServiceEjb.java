package org.elhg.webapp.ejb.service;

import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Stateful
//@Stateless
public class ServiceEjb {

    private int contador;

    public String saludar(String nombre) {
        System.out.println("imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en metodo saludar " + contador);
        return "Hola que tal " + nombre;
    }
}
