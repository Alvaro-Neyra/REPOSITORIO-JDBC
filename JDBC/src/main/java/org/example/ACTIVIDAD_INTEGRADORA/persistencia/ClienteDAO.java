package org.example.ACTIVIDAD_INTEGRADORA.persistencia;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Cliente;
import org.example.ACTIVIDAD_INTEGRADORA.entidades.Familia;

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

    public List<Cliente> listarClientesConEstanciaReservada() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT c.nombre AS nombre_cliente, c.calle, c.numero, c.ciudad, c.pais, ca.tipo_vivienda, ca.precio_habitacion, ca.fecha_desde, cs.fecha_hasta " + " " +
                    "FROM clientes c JOIN familias f ON c.id_cliente = f.id_familia  JOIN casas ca ON f.id_casa_familia = ca.id_casa";
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

    public List<Cliente> ListarClientesMostrandoInfo() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Cliente cliente = new Cliente();
            Familia familia = new Familia();
            String sql = "SELECT c.nombre AS nombre_cliente, c.pais, c.ciudad, ca.tipo_vivienda, ca.precio_habitacion, ca.fecha_desde, ca.fecha_hasta" +
            "FROM clientes c" +"JOIN familias f ON c.id_cliente = f.id_familia JOIN casas ca ON f.id_casa_familia = ca.id_casa";
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
        } catch(Exception e) {
            throw new Exception("Error al listar clientes mostrando info: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
        return clientes;
    }
}
