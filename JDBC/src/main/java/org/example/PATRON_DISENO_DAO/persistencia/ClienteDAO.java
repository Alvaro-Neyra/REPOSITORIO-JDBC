package org.example.PATRON_DISENO_DAO.persistencia;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;

import java.util.List;
import java.util.ArrayList;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }

        try {
            String sql = "INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito)" +
                        "VALUES ( '" + cliente.getCodigoCliente() + "' , '" + cliente.getNombreCliente() + "' , '" + cliente.getNombreContacto() + "' , '" + cliente.getApellidoContacto() + "' , '" + cliente.getTelefono() + "' , '"
                        + cliente.getFax() + "' , '" + cliente.getCiudad() + "' , '" + cliente.getRegion() + "' , '" + cliente.getPais() + "' , '" + cliente.getCodigoPostal() + "' , '" + cliente.getIdEmpleado() + "' , '" + cliente.getLimiteCredito() + "')";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Cliente> listarTodosLosClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            Cliente cliente = null;
            String sql = "SELECT * FROM cliente";
            consultarBase(sql);

            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getInt("limite_credito"));
                clientes.add(cliente);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception(e.getMessage());
        }
        return clientes;
    }
    public List<Cliente> buscarClientePorCodigo(int codigo) throws Exception {
        List<Cliente> clientesPorCodigo = new ArrayList<>();
        try {
            Cliente cliente = null;
            String sql = "SELECT * FROM cliente WHERE codigo_cliente = '" + codigo + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getInt("limite_credito"));
                clientesPorCodigo.add(cliente);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al encontrar el cliente: " + e.getMessage());
        }
        return clientesPorCodigo;
    }
    public void eliminarClientePorCodigo(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM cliente WHERE codigo_cliente = '" + codigo + "'";
            insertarModificarEliminar(sql);
            System.out.println("Cliente eliminado: " + codigo);
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al eliminar el cliente: " + e.getMessage());
        }
    }
    public List<Cliente> listarClientesPorEmpleado(int idEmpleado) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente WHERE id_empleado = '" + idEmpleado + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getInt("limite_credito"));
                clientes.add(cliente);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error al listar los clientes por empleado: " + e.getMessage());
        }
        return clientes;
    }
    public void incrementarLimiteCredito() throws Exception {
        try {
            String sql = "UPDATE cliente SET limite_credito = limite_credito * 1.15";
            insertarModificarEliminar(sql);
            System.out.println("Todos los clientes han aumentado su limite de credito a 15%");
        } catch (Exception e) {
            throw new Exception("Error al incrementar el credito: " + e.getMessage());
        }
    }
}
