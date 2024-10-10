package org.example.PATRON_DISENO_DAO.servicios;

import java.util.ArrayList;
import java.util.List;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;
import org.example.PATRON_DISENO_DAO.persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO clienteDAO;
    public ClienteServicio() {
        clienteDAO = new ClienteDAO();
    }
    public Cliente crearNuevoCliente(int codigoC, String nombre, String nombreContacto, String apellidoContacto,
                                     String telefono, String fax, String ciudad, String region, String pais, String codigoPostal,
                                     int idEmpleado, double limiteCredito) throws Exception {
        verificarNombreYApellido(nombreContacto, apellidoContacto);
        Cliente cliente = new Cliente(codigoC, nombre, nombreContacto, apellidoContacto,telefono, fax,ciudad, region, pais, codigoPostal, idEmpleado, limiteCredito);
        clienteDAO.guardarCliente(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> clientesRecibidos = new ArrayList<>();
        try {
            clientesRecibidos = clienteDAO.listarTodosLosClientes();
            if (clientesRecibidos.isEmpty()) {
                System.out.println("No hay clientes encontrados.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return clientesRecibidos;
    }

    public List<Cliente> buscarClientesPorCodigo(int codigoCliente) throws Exception {
        verificarCodigo(codigoCliente);
        List<Cliente> clientesRecibidos = new ArrayList<>();
        try {
            clientesRecibidos = clienteDAO.buscarClientePorCodigo(codigoCliente);
            if (clientesRecibidos.isEmpty()) {
                System.out.println("No hay clientes encontrados.");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return clientesRecibidos;
    }

    public void eliminarCliente(int codigoCliente) throws Exception {
        verificarCodigo(codigoCliente);
        clienteDAO.eliminarClientePorCodigo(codigoCliente);
    }

    public List<Cliente> listarClientePorIdEmpleado(int idEmpleado) throws Exception {
        verificarCodigo(idEmpleado);
        List<Cliente> clientesRecibidosPorEmpleado = new ArrayList<>();
        clientesRecibidosPorEmpleado = clienteDAO.listarClientesPorEmpleado(idEmpleado);
        if (clientesRecibidosPorEmpleado.isEmpty()) {
            System.out.println("No hay clientes encontrados.");
        }
        return clientesRecibidosPorEmpleado;
    }

    public void incrementarLimiteCreditoClientes() throws Exception {
        clienteDAO.incrementarLimiteCredito();
        System.out.println("Limite incrementado en un 15% a todos los clientes.");
    }

    private void verificarNombreYApellido(String nombreContacto, String apellidoContacto) throws Exception {
        if (nombreContacto == null || nombreContacto.equals("")) {
            throw new Exception("El nombre del cliente no puede ser vacio");
        }

        if (apellidoContacto == null || apellidoContacto.equals("")) {
            throw new Exception("El apellido del cliente no puede ser vacio");
        }
    }
    private void verificarCodigo(int codigoCliente) throws Exception {
        if (codigoCliente <= 0) {
            throw new Exception("El codigo de cliente no puede ser negativo o cero");
        }
    }
}
