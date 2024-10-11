package org.example.ACTIVIDAD_INTEGRADORA.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import io.github.cdimascio.dotenv.Dotenv;

public abstract class DAO {
    Dotenv dotenv = Dotenv.load();
    protected Connection conexion = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = dotenv.get("DB_USER");
    private final String PASSWORD = dotenv.get("DB_PASSWORD");
    private final String DATABASE = "estancias_exterior";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String ZONA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    protected void connectarBaseDeDatos() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + ZONA;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void desconectarBaseDeDatos() throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void insertarModificarEliminar(String sql) throws SQLException, ClassNotFoundException {
        try {
            connectarBaseDeDatos();
            statement = conexion.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            // En el caso de que haigan muchas inserciones y durante el transcurso hay un error pues se eliminan las anteriores inserciones
            // conexion.rollback();
            throw e;
        } finally {
            desconectarBaseDeDatos();
        }
    }

    protected void consultarBase(String sql) throws Exception {
        try {
            connectarBaseDeDatos();
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
