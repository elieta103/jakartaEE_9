package org.elhg.clientews;

import jakarta.xml.ws.BindingProvider;
import org.elhg.webapp.jaxws.services.Curso;
import org.elhg.webapp.jaxws.services.CursoServicioWs;
import org.elhg.webapp.jaxws.services.CursoServicioWsImplService;

public class Main {
    public static void main(String[] args) {
        CursoServicioWs service = new CursoServicioWsImplService().getCursoServicioWsImplPort();

        ((BindingProvider)service).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "eliel");
        ((BindingProvider)service).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "12345");

        service.listar().forEach(c -> System.out.println(c.getNombre()));

        Curso curso = new Curso();
        curso.setNombre("react");
        curso.setDescripcion("react js");
        curso.setDuracion(50D);
        curso.setInstructor("andres guzman");
        Curso respuesta = service.guardar(curso);
        System.out.println("nuevo curso: " + curso.getId() +", "+ curso.getNombre());

        service.listar().forEach(c -> System.out.println(c.getNombre()));
    }
}
