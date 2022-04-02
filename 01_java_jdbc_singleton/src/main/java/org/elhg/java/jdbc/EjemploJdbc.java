package org.elhg.java.jdbc;

import org.elhg.java.jdbc.modelo.Producto;
import org.elhg.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.elhg.java.jdbc.repositorio.Repositorio;
import org.elhg.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String... args) {
        // Closes this resource, relinquishing any underlying resources.
        // This method is invoked automatically on objects managed by the try-with-resources statement.
        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============= Listar productos =============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============= Obtener producto por id =============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============= Insertar nuevo producto =============");
            Producto producto = new Producto();
            producto.setNombre("Teclado mecánico");
            producto.setPrecio(500);
            producto.setFechaRegistro(new Date());
            repositorio.guardar(producto);
            System.out.println("Producto guardado con éxito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
