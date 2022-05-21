package org.elhg.webapp.ejb.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elhg.webapp.ejb.service.ServiceEjb;

import java.io.IOException;

@WebServlet("/index")
public class EjemploServlet extends HttpServlet {
    //@EJB es el contexto del Ejb
    //@Inject es el contexto de CDI

    // @Stateless y @EJB
    // service si es igual a service2 = true
    // contador 1, 2, 3, 4, 5, 6

    // @Stateful y @EJB
    // service si es igual a service2 = false
    // contador 1, 1, 2, 2, 3, 3

    //@Stateful @RequestScoped y @EJB   (@RequestScoped no funciona con @EJB)
    // service si es igual a service2 = false
    // contador 1, 1, 2, 2, 3, 3

    //@Stateful @RequestScoped y @Inject
    // service si es igual a service2 = true
    // contador 1, 2, 1, 2, 1, 2

    @Inject
    private ServiceEjb service;

    @Inject
    private ServiceEjb service2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service si es igual a service2 = " + service.equals(service2));
        req.setAttribute("saludo", service.saludar("Andres"));
        req.setAttribute("saludo2", service2.saludar("John"));
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
