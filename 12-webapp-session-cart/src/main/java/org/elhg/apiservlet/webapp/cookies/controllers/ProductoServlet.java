package org.elhg.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elhg.apiservlet.webapp.cookies.models.Producto;
import org.elhg.apiservlet.webapp.cookies.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos-html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUserName(req);

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de Productos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Listado de Productos!</h1>");
            if(usernameOptional.isPresent()){
                out.println("<div style='color:blue;'>Hola "+usernameOptional.get()+" Bienvenido !</div>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th><b>Id</b></th>");
            out.println("<th><b>Nombre</b></th>");
            out.println("<th><b>Tipo</b></th>");
            if(usernameOptional.isPresent()) {
                out.println("<th><b>Precio</b></th>");
                out.println("<th><b>Agregar</b></th>");
            }
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href=\""+req.getContextPath()+"/agregar-carro?id="+p.getId()+"\">Agregar carro</a></td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");

            out.println("    </body>");
            out.println("</html>");
        }
    }
}
