package org.example.ACTIVIDAD_INTEGRADORA.persistencia;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Casa;

import java.util.ArrayList;
import java.util.List;

public class CasaDAO extends DAO{
    public void guardarCasa(Casa casa) throws Exception{
        if (casa == null) {
            throw new Exception("La casa no puede ser nulo");
        }

        try {
            String sql = "INSERT INTO casas (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) VALUES('"+ casa.getCalle() +"',"+ casa.getNumero() +",'" + casa.getCodigoPostal() + "','" +casa.getCiudad()+ "','" + casa.getPais()+ "','" + casa.getFechaDesde()+ "','" +casa.getFechaHasta()+ "',"+ casa.getTiempoMinimo()+"," +casa.getTiempoMaximo()+ "," +casa.getPrecioHabitacion()+ ",'" +casa.getTipoVivienda()+ "')";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Casa> buscarCasa(int idCasa) throws Exception{
        if (idCasa < 0) {
            throw new Exception("La casa no puede ser nulo");
        }

        List<Casa> casas = new ArrayList<Casa>();

        try {
            Casa casa = null;
            String sql = "SELECT * FROM casas WHERE id_casa = '" + idCasa + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getDate("fecha_desde"));
                casa.setFechaHasta(resultSet.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_vivienda"));
                casas.add(casa);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al encontrar la casa" + e.getMessage());
        }
        return casas;
    }

    public List<Casa> listarCasas() throws Exception {
        List<Casa> casas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM casas";
            consultarBase(sql);

            while (resultSet.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getDate("fecha_desde"));
                casa.setFechaHasta(resultSet.getDate("fecha_hasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipoVivienda(resultSet.getString("tipo_vivienda"));
                casas.add(casa);
            }
        } catch (Exception e) {
            throw new Exception("Error al listar las casas" + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return casas;
    }
}
