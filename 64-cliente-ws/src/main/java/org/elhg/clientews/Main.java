package org.elhg.clientews;

import org.elhg.webapp.jaxws.services.Curso;
import org.elhg.webapp.jaxws.services.ServicioWs;
import org.elhg.webapp.jaxws.services.ServicioWsImplService;

public class Main {
    public static void main(String[] args) {
        ServicioWs service = new ServicioWsImplService().getServicioWsImplPort();
        System.out.println("El saludo del WebService: " + service.saludar("Eliel"));

        Curso curso = new Curso();
        curso.setNombre("Angular");
        Curso respuesta = service.crear(curso);
        System.out.println("Nuevo curso: " + curso.getNombre());

        service.listar().forEach(c->System.out.println(c.getNombre()));
    }
}
