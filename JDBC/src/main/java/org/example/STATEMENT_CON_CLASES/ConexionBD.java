package org.example.STATEMENT_CON_CLASES;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DATABASE = "vivero";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String ZONA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "root";

    protected Connection conectar() throws ClassNotFoundException, SQLException {
        try {
            Dotenv dotenv = Dotenv.load();
            String PASSWORD = dotenv.get("DB_PASSWORD");
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + ZONA + "&useSSL=false";
            return DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
}
