package org.example.PATRON_DISENO_DAO.persistencia;

import org.example.PATRON_DISENO_DAO.entidades.Oficina;

import java.util.ArrayList;
import java.util.List;

public class OficinaDAO extends DAO{
    public List<Oficina> buscarOficinaPorCodigo(int codigo) throws Exception {
        List<Oficina> oficinas = new ArrayList<Oficina>();
        try {
            Oficina oficina = new Oficina();
            String sql = "SELECT * FROM oficina WHERE codigo = " + codigo;
            consultarBase(sql);
            while (resultSet.next()) {
                oficina.setIdOficina(resultSet.getInt("id_oficina"));
                oficina.setCodigoOficina(resultSet.getString("codigo_oficina"));
                oficina.setCiudad(resultSet.getString("ciudad"));
                oficina.setPais(resultSet.getString("pais"));
                oficina.setRegion(resultSet.getString("region"));
                oficina.setCodigoPostal(resultSet.getString("codigo_postal"));
                oficina.setTelefono(resultSet.getString("telefono"));
                oficinas.add(oficina);
            }
            desconectarBaseDeDatos();
        } catch(Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al buscar oficina por codigo: " + e.getMessage());
        }
        return oficinas;
    }

    public void modificarOficina(int idOficina, String codigoOficina, String ciudad, String pais, String region, String codigoPostal, String telefono) throws Exception {
        try {
            String sql = "UPDATE oficina SET codigo_oficina = '" + codigoOficina + " ', ciudad = ' " + ciudad + " ', pais = '" + pais + " ', region = '" + region + " ', codigo_postal = '" + codigoPostal + " ', telefono = '" + telefono + "' WHERE id_oficina = " + idOficina;
            insertarModificarEliminar(sql);
            System.out.println("Oficina modificada con exito!");
        } catch (Exception e) {
            throw new Exception("Error al modificar oficina por codigo: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
    }
}
