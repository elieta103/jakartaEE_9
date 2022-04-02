package org.elhg.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.elhg.java.jdbc.util.UtilsConstants.*;

public class ConexionBaseDatos {

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }
        return connection;
    }
}
