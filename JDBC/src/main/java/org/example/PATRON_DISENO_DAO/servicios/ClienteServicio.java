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
        verificarCliente(codigoC, nombre, nombreContacto, apellidoContacto, telefono, idEmpleado, limiteCredito);

        if (verificarClienteRepetido(codigoC)) {
            System.out.println("El cliente ya existe");
            return buscarClientesPorCodigo(codigoC).getFirst();
        }

        Cliente cliente = new Cliente(codigoC, nombre, nombreContacto, apellidoContacto,telefono, fax,ciudad, region, pais, codigoPostal, idEmpleado, limiteCredito);
        clienteDAO.guardarCliente(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() throws Exception {
        List<Cliente> clientesRecibidos = new ArrayList<>();
        try {
            clientesRecibidos = clienteDAO.listarTodosLosClientes();
            if (clientesRecibidos.isEmpty()) {
                System.out.println("No hay clientes existentes.");
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
    private boolean verificarClienteRepetido(int codigoCliente) throws Exception{
        List<Cliente> clientes = buscarClientesPorCodigo(codigoCliente);
        return !clientes.isEmpty();
    }
    private void verificarCliente(int codigoC, String nombre, String nombreContacto, String apellidoContacto, String telefono, int idEmpleado, double limiteCredito) throws Exception {
        if (codigoC <= 0) {
            throw new Exception("El código del cliente debe ser positivo");
        }
        if (idEmpleado <= 0) {
            throw new Exception("El ID del empleado debe ser positivo");
        }
        if (limiteCredito < 0) {
            throw new Exception("El límite de crédito no puede ser negativo");
        }
        if (nombre == null || nombre.trim().isEmpty() || nombreContacto == null || nombreContacto.trim().isEmpty() || apellidoContacto == null || apellidoContacto.trim().isEmpty()) {
            throw new Exception("El nombre, nombre de contacto y apellido de contacto no pueden estar vacíos");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new Exception("El teléfono no puede estar vacío");
        }
    }
}
