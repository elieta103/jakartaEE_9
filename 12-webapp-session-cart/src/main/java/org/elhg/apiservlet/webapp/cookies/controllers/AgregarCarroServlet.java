package org.elhg.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.elhg.apiservlet.webapp.cookies.models.Carro;
import org.elhg.apiservlet.webapp.cookies.models.ItemCarro;
import org.elhg.apiservlet.webapp.cookies.models.Producto;
import org.elhg.apiservlet.webapp.cookies.services.ProductoService;
import org.elhg.apiservlet.webapp.cookies.services.ProductoServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductoService service = new ProductoServiceImpl();
        Optional<Producto> producto = service.porId(id);
        Carro carro = null;
        if(producto.isPresent()){
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            if(session.getAttribute("carro") == null){
                carro = new Carro();
                session.setAttribute("carro", carro);
            }else{
                carro = (Carro)session.getAttribute("carro");
            }
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath()+"/ver-carro");
    }
}
