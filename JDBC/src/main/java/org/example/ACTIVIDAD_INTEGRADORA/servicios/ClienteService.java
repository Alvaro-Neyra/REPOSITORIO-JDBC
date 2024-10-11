package org.example.ACTIVIDAD_INTEGRADORA.servicios;

import org.example.ACTIVIDAD_INTEGRADORA.entidades.Cliente;
import org.example.ACTIVIDAD_INTEGRADORA.persistencia.ClienteDAO;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO;
    public ClienteService() {
        clienteDAO = new ClienteDAO();
    }
    public Cliente agregarCliente(Cliente cliente) throws Exception {
        verificarCliente(cliente);
        if (verificarClienteDuplicado(cliente.getIdCliente())) {
            System.out.println("Cliente ya existente no se puede volver a agregar");
            return buscarCliente(cliente.getIdCliente()).getFirst();
        }
        clienteDAO.guardarCliente(cliente);
        return cliente;
    }

    public List<Cliente> buscarCliente(int idCliente) throws Exception {
        verificarCodigo(idCliente);
        List<Cliente> clientesBuscados = new ArrayList<>();
        try {
            clientesBuscados = clienteDAO.buscarCliente(idCliente);
            if (clientesBuscados.isEmpty()) {
                System.out.println("Cliente(s) no encontrado(s)");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return clientesBuscados;
    }

    public List<Cliente> listarClientes() throws Exception{
        List<Cliente> clientesAListar = new ArrayList<>();
        try {
            clientesAListar = clienteDAO.listarClientes();
            if (clientesAListar.isEmpty()) {
                System.out.println("No hay clientes existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return clientesAListar;
    }

    private boolean verificarClienteDuplicado(int idCliente) throws Exception {
        List<Cliente> clientesAVerificar = buscarCliente(idCliente);
        return !clientesAVerificar.isEmpty();
    }


    private void verificarCodigo(int idCliente) throws Exception{
        if (idCliente < 0) {
            throw new Exception("El id del cliente no puede ser negativa");
        }
    }

    private void verificarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new Exception("El nombre del cliente no puede ser nulo o vacío.");
        }
        if (cliente.getCalle() == null || cliente.getCalle().isEmpty()) {
            throw new Exception("La calle del cliente no puede ser nula o vacía.");
        }
        if (cliente.getCodigoPostal() == null || cliente.getCodigoPostal().isEmpty()) {
            throw new Exception("El código postal no puede ser nulo o vacío.");
        }
        if (cliente.getCiudad() == null || cliente.getCiudad().isEmpty()) {
            throw new Exception("La ciudad del cliente no puede ser nula o vacía.");
        }
        if (cliente.getPais() == null || cliente.getPais().isEmpty()) {
            throw new Exception("El país del cliente no puede ser nulo o vacío.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new Exception("El email del cliente no puede ser nulo o vacío.");
        }
    }
}
