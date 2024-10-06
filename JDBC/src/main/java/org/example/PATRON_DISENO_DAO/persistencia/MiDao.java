package org.example.PATRON_DISENO_DAO.persistencia;

import java.sql.SQLException;

public class MiDao extends DAO{
    public void realizarConsulta() {
        try {
            connectarBaseDeDatos();
            statement = conexion.createStatement();
            String sql = "SELECT * FROM cliente";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println("Resultado: " + resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        finally {
            try {
                desconectarBaseDeDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
