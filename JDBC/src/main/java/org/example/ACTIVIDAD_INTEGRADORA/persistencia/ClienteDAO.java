package org.example.ACTIVIDAD_INTEGRADORA.persistencia;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        try {
            String sql = "INSERT INTO clientes (nombre, calle, numero, codigo_postal, ciudad, pais, email) VALUES ('"
                    + cliente.getNombre() + "', '" + cliente.getCalle() + "', " + cliente.getNumero() + ", '"
                    + cliente.getCodigoPostal() + "', '" + cliente.getCiudad() + "', '" + cliente.getPais() + "', '"
                    + cliente.getEmail() + "');";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Cliente> buscarCliente(int idCliente) throws Exception {
        if (idCliente < 0) {
            throw new Exception("Id no valido");
        }

        List<Cliente> clientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM clientes WHERE id_cliente = " + idCliente;
            consultarBase(sql);
            while (resultSet.next()) {
                Cliente clienteNuevo = new Cliente();
                clienteNuevo.setIdCliente(resultSet.getInt("id_cliente"));
                clienteNuevo.setNombre(resultSet.getString("nombre"));
                clienteNuevo.setCalle(resultSet.getString("calle"));
                clienteNuevo.setNumero(resultSet.getInt("numero"));
                clienteNuevo.setCodigoPostal(resultSet.getString("codigo_postal"));
                clienteNuevo.setCiudad(resultSet.getString("ciudad"));
                clienteNuevo.setPais(resultSet.getString("pais"));
                clienteNuevo.setEmail(resultSet.getString("email"));
                clientes.add(clienteNuevo);
            }
        } catch (Exception e) {
            throw new Exception("Error al buscar cliente: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return clientes;
    }

    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clientes";
            consultarBase(sql);

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            throw new Exception("Error al listar clientes: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return clientes;
    }
}
