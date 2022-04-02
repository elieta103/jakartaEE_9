package org.elhg.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/despachar")
public class DespacharServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // En el redirect,   Si realiza un nuevo request, diferente al existente, si Cambia la url
        // En el dispatcher, NO realiza un nuevo request, el existente lo une al recurso, No cambia la url, es un JOiN
        getServletContext().getRequestDispatcher("/productos-html").forward(req, resp);
    }
}
