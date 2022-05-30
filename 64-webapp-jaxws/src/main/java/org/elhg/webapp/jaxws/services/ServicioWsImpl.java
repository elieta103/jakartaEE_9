package org.elhg.webapp.jaxws.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.elhg.webapp.jaxws.models.Curso;

import java.util.Arrays;
import java.util.List;

@WebService(endpointInterface = "org.elhg.webapp.jaxws.services.ServicioWs")
public class ServicioWsImpl implements ServicioWs{
    private int contador;

    @WebMethod
    @Override
    public String saludar(String persona) {
        System.out.println("Imprimiendo dentro del servicio web con instancia: " + this);
        contador++;
        System.out.println("Valor del contador en metodo saludar es " + contador);
        return "Hola que tal " + persona;
    }

    @WebMethod
    @Override
    public List<Curso> listar() {
        return Arrays.asList(new Curso("java"), new Curso("spring"), new Curso("jakarta ee"));
    }

    @WebMethod
    @Override
    public Curso crear(Curso curso) {
        System.out.println("Curso guardado con exito ... " + curso.getNombre());
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(curso.getNombre());
        return nuevoCurso;
    }
}
