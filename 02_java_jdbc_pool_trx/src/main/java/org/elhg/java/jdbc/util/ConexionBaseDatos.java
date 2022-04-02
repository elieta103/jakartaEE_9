package org.elhg.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.elhg.java.jdbc.util.UtilsConstants.PASSWORD;
import static org.elhg.java.jdbc.util.UtilsConstants.URL;
import static org.elhg.java.jdbc.util.UtilsConstants.USER_NAME;

public class ConexionBaseDatos {
    private static BasicDataSource pool;

    // Devuelve el pool de coenxiones
    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(USER_NAME);
            pool.setPassword(PASSWORD);
            pool.setInitialSize(3);  //Conexiones abiertas habilitadas
            pool.setMinIdle(3);      //Minimo de conexiones, esperando para ser usadas
            pool.setMaxIdle(8);      //Maximo de conexiones, esperando para ser usadas
            pool.setMaxTotal(8);     //Maximo de conexiones entre las usadas y las disponibles para usarse
        }
        return pool;
    }

    // Devuelve la conexion
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
