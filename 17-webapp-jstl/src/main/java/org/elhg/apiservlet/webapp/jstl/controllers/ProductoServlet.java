package org.elhg.apiservlet.webapp.jstl.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elhg.apiservlet.webapp.jstl.models.Producto;
import org.elhg.apiservlet.webapp.jstl.services.LoginService;
import org.elhg.apiservlet.webapp.jstl.services.LoginServiceSessionImpl;
import org.elhg.apiservlet.webapp.jstl.services.ProductoService;
import org.elhg.apiservlet.webapp.jstl.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}