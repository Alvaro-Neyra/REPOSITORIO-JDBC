package org.example.PATRON_DISENO_DAO.servicios;

import java.sql.Date;
import org.example.PATRON_DISENO_DAO.entidades.Pedido;
import org.example.PATRON_DISENO_DAO.persistencia.PedidosDAO;
import java.util.List;

public class PedidoServicio {
    private PedidosDAO pedidosDAO;
    public PedidoServicio() {
        pedidosDAO = new PedidosDAO();
    }
    public Pedido crearNuevoPedido(int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado, String comentarios, int idCliente) throws Exception{
        verificarDatosPedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente);
        if (verificarPedidoRepetido(codigoPedido)) {
            System.out.println("El pedido ya existe!");
            return buscarPedidoPorCodigo(codigoPedido).getFirst();
        }
        Pedido pedidoAAgregar = new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente);
        pedidosDAO.guardarPedido(pedidoAAgregar);
        return pedidoAAgregar;
    }

    public List<Pedido> buscarPedidoPorCodigo(int codigoPedido) throws Exception{
        verificarCodigo(codigoPedido);
        List<Pedido> pedidosEncontrados = null;
        try {
            pedidosEncontrados = pedidosDAO.buscarPedidoPorCodigo(codigoPedido);
            if (pedidosEncontrados.isEmpty()) {
                System.out.println("Pedido no encontrado");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return pedidosEncontrados;
    }

    public List<Pedido> listarPedidosPorClienteID(int idCliente) throws Exception {
        verificarCodigo(idCliente);
        List<Pedido> pedidos = pedidosDAO.listarPedidosPorCliente(idCliente);
        if (pedidos.isEmpty()) {
            System.out.println("Pedido no encontrado");
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorEstado(String estado) throws Exception {
        verificarString(estado);
        List<Pedido> pedidosPorEstado = pedidosDAO.listarPedidosPorEstado(estado);
        if (pedidosPorEstado.isEmpty()) {
            System.out.println("Pedido no encontrado");
        }
        return pedidosPorEstado;
    }

    public List<Pedido> listarPedidosPorProductoID(int idProducto) throws Exception {
        verificarCodigo(idProducto);
        List<Pedido> pedidosPorProducto = pedidosDAO.listarPedidosPorProducto(idProducto);
        if (pedidosPorProducto.isEmpty()) {
            System.out.println("Pedido no encontrado");
        }
        return pedidosPorProducto;
    }

    private void verificarString(String cadena) throws Exception{
        if (cadena.isEmpty()) {
            throw new Exception("Atributo no valido");
        }
    }

    private boolean verificarPedidoRepetido(int codigoPedido) throws Exception{
        List<Pedido> pedidos = buscarPedidoPorCodigo(codigoPedido);
        return !pedidos.isEmpty(); // Si es true existe oficinas con ese codigo especificado
    }

    private void verificarCodigo(int codigoPedido) throws Exception {
        if (codigoPedido < 1) {
            throw new Exception("Codigo de pedido no valido");
        }
    }

    private void verificarDatosPedido(int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado, String comentarios, int idCliente) throws Exception{
        if (codigoPedido < 0) {
            throw new Exception("El codigo pedido no puede ser negativo");
        }

        if (fechaEntrega.before(fechaPedido)) {
            throw new Exception("La fecha de entrega no puede ser antes de la fecha de pedido");
        }

        if (fechaPedido == null || fechaEntrega == null || fechaEsperada == null) {
            throw new Exception("La fechas no pueden ser nulas.");
        }

        if (estado == null || estado.isEmpty()) {
            throw new Exception("El estado no puede ser nulo.");
        }

        if (comentarios == null || comentarios.isEmpty()) {
            throw new Exception("El comentarios no puede ser nulo.");
        }

        if (idCliente < 0) {
            throw new Exception("El idCliente no puede ser negativo.");
        }
    }
}
