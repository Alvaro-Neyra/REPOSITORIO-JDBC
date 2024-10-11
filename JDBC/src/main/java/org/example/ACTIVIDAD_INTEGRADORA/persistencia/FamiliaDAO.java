package org.example.ACTIVIDAD_INTEGRADORA.persistencia;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Familia;

import java.util.ArrayList;
import java.util.List;

public class FamiliaDAO extends DAO {
    public void guardarFamilia(Familia familia) throws Exception {
        try {
            String sql = "INSERT INTO familias (nombre, edad_minima, edad_maxima, num_hijos, email) VALUES ('"
                    + familia.getNombre() + "', " + familia.getEdadMinima() + ", " + familia.getEdadMaxima() + ", "
                    + familia.getNumHijos() + ", '" + familia.getEmail() + "');";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Familia> buscarFamilia(int idFamilia) throws Exception{
        if (idFamilia < 0) {
            throw new Exception("El id de la familia especificada no puede ser nulo");
        }

        List<Familia> familiasEncontradas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM familia WHERE id_familia = " + idFamilia;
            consultarBase(sql);
            while (resultSet.next()) {
                Familia nuevaFamilia = new Familia();
                nuevaFamilia.setIdFamilia(resultSet.getInt("id_familia"));
                nuevaFamilia.setNombre(resultSet.getString("nombre"));
                nuevaFamilia.setEdadMinima(resultSet.getInt("edad_minima"));
                nuevaFamilia.setEdadMaxima(resultSet.getInt("edad_maxima"));
                nuevaFamilia.setNumHijos(resultSet.getInt("num_hijos"));
                nuevaFamilia.setEmail(resultSet.getString("email"));
                nuevaFamilia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
                familiasEncontradas.add(nuevaFamilia);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar familia: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return familiasEncontradas;
    }

    public List<Familia> listarFamilias() throws Exception{
        List<Familia> familiasListadas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias";
            consultarBase(sql);
            while (resultSet.next()) {
                Familia nuevaFamilia = new Familia();
                nuevaFamilia.setIdFamilia(resultSet.getInt("id_familia"));
                nuevaFamilia.setNombre(resultSet.getString("nombre"));
                nuevaFamilia.setEdadMinima(resultSet.getInt("edad_minima"));
                nuevaFamilia.setEdadMaxima(resultSet.getInt("edad_maxima"));
                nuevaFamilia.setNumHijos(resultSet.getInt("num_hijos"));
                nuevaFamilia.setEmail(resultSet.getString("email"));
                nuevaFamilia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
                familiasListadas.add(nuevaFamilia);
            }
        } catch (Exception e) {
            throw new Exception("Error al listar familias: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return familiasListadas;
    }
}
