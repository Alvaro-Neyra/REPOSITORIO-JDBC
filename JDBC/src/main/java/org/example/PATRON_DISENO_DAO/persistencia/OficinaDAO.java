package org.example.PATRON_DISENO_DAO.persistencia;

import org.example.PATRON_DISENO_DAO.entidades.Oficina;

import java.util.ArrayList;
import java.util.List;

public class OficinaDAO extends DAO{

    public void agregarOficina(Oficina oficina) throws Exception {
        if (oficina == null) {
            throw new Exception("Oficina no puede ser nulo");
        }

        try {
            String sql = "INSERT INTO oficina (codigo_oficina, ciudad, pais, region, codigo_postal, telefono) " +
                    "VALUES ('" + oficina.getCodigoOficina() + "', '" + oficina.getCiudad() + "', '" + oficina.getPais() + "', '" +
                    oficina.getRegion() + "', '" + oficina.getCodigoPostal() + "', '" + oficina.getTelefono() + "')";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception("Error al insertar la oficina: " + e.getMessage());
        }
    }


    public List<Oficina> buscarOficinaPorCodigo(String codigoOficina) throws Exception {
        List<Oficina> oficinas = new ArrayList<Oficina>();
        try {
            Oficina oficina = new Oficina();
            String sql = "SELECT * FROM oficina WHERE codigo_oficina = '" + codigoOficina + "'";
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

    public void modificarOficina(Oficina oficina) throws Exception {
        try {
            String sql = "UPDATE oficina SET codigo_oficina = '" + oficina.getCodigoOficina() + " ', ciudad = ' " + oficina.getCiudad() + " ', pais = '" + oficina.getPais() + " ', region = '" + oficina.getRegion() + " ', codigo_postal = '" + oficina.getCodigoPostal() + " ', telefono = '" + oficina.getTelefono() + "' WHERE id_oficina = " + oficina.getIdOficina();
            insertarModificarEliminar(sql);
            System.out.println("Oficina modificada con exito!");
        } catch (Exception e) {
            throw new Exception("Error al modificar oficina por codigo: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
    }
}
