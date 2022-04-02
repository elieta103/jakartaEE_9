package org.elhg.java.jdbc.repositorio;

import org.elhg.java.jdbc.modelo.Producto;
import org.elhg.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto>{

    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    //  try (Initialize resources to close.)

    // The try-with-resources statement is a try statement that declares one or more resources.
    // A resource is an object that must be closed after the program is finished with it.
    // The try-with-resources statement ensures that each resource is closed at the end of the statement.
    // Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.


    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {
            while (rs.next()) {
                Producto p = obtenerProducto(rs);
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }


    @Override
    public Producto porId(Long id) {
        Producto producto = null;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = obtenerProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES(?,?,?)";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3, producto.getId());   //UPDATE
            } else {
                stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(Long id) {

        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Producto obtenerProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        return p;
    }
}
