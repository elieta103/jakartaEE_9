package org.elhg.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.elhg.apiservlet.webapp.cookies.services.LoginService;
import org.elhg.apiservlet.webapp.cookies.services.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Validar la primera vez va vacio

        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUserName(req);

        if(cookieOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Hola "+cookieOptional.get()+" </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hola "+cookieOptional.get()+"  has iniciado session con éxito!</h1>");
            out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver</a></p>");
            out.println("<p><a href='"+req.getContextPath()+"/logout'>Cerrar Sessión</a></p>");

            out.println("</body>");
            out.println("</html>");
            out.close();
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);

            resp.sendRedirect(req.getContextPath()+"/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
