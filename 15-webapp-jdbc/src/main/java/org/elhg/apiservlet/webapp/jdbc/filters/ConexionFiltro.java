package org.elhg.apiservlet.webapp.jdbc.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.elhg.apiservlet.webapp.jdbc.services.ServiceJdbcException;
import org.elhg.apiservlet.webapp.jdbc.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter({"/*"})
public class ConexionFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try(Connection conn = ConexionBaseDatos.getConnection()){
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                request.setAttribute("conn", conn);
                chain.doFilter(request, response);  // Execute Servlets
                conn.commit();
            }catch (SQLException | ServiceJdbcException ex){
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
                ex.printStackTrace();
            }
        }catch (SQLException sqlex){
            sqlex.printStackTrace();
        }

    }
}
