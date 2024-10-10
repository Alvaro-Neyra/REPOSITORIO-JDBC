package org.example.PATRON_DISENO_DAO.persistencia;

import org.example.PATRON_DISENO_DAO.entidades.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidosDAO extends DAO {

    public void guardarPedido(Pedido pedido) throws Exception {
        if (pedido == null) {
            throw new Exception("Pedido no puede ser nulo");
        }
        try {
            String sql = "INSERT INTO pedido (id_pedido, codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, id_cliente) " +
                    "VALUES (" + pedido.getIdPedido() + ", "+ pedido.getCodigoPedido() + ", '" + pedido.getFechaPedido() + "', '"+ pedido.getFechaEsperada() + "', '"+ pedido.getFechaEntrega() +"', '"+ pedido.getEstado() +"', '"+ pedido.getComentarios()+"', " + pedido.getIdCliente() +")";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public List<Pedido> listarPedidosPorCliente(int clienteID) throws Exception{
        List<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            Pedido pedido = new Pedido();
            String sql = "SELECT * FROM pedido WHERE id_cliente = " + clienteID;
            consultarBase(sql);
            while (resultSet.next()) {
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setCodigoPedido(resultSet.getInt("codigo_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setFechaEsperada(resultSet.getDate("fecha_esperada"));
                pedido.setFechaEntrega(resultSet.getDate("fecha_entrega"));
                pedido.setEstado(resultSet.getString("estado"));
                pedido.setComentarios(resultSet.getString("comentarios"));
                pedido.setIdCliente(resultSet.getInt("id_cliente"));
                pedidos.add(pedido);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error a listar pedidos del cliente" + e.getMessage());
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorEstado(String estado) throws Exception{
        List<Pedido> pedidosPorEstado = new ArrayList<>();
        try {
            Pedido pedido = new Pedido();
            String sql = "SELECT * FROM pedido WHERE estado = '" + estado + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setCodigoPedido(resultSet.getInt("codigo_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setFechaEsperada(resultSet.getDate("fecha_esperada"));
                pedido.setFechaEntrega(resultSet.getDate("fecha_entrega"));
                pedido.setEstado(resultSet.getString("estado"));
                pedido.setComentarios(resultSet.getString("comentarios"));
                pedido.setIdCliente(resultSet.getInt("id_cliente"));
                pedidosPorEstado.add(pedido);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error a listar pedido del estado" + e.getMessage());
        }
        return pedidosPorEstado;
    }

    public List<Pedido> listarPedidosPorProducto(int idProducto) throws Exception{
        List<Pedido> pedidosPorProducto = new ArrayList<>();
        try {
            Pedido pedido = new Pedido();
            String sql = "SELECT p.* FROM pedido `p` JOIN detalle_pedido `dp` ON dp.id_pedido = p.id_pedido WHERE dp.id_pedido = " + idProducto;
            consultarBase(sql);
            while (resultSet.next()) {
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setCodigoPedido(resultSet.getInt("codigo_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setFechaEsperada(resultSet.getDate("fecha_esperada"));
                pedido.setFechaEntrega(resultSet.getDate("fecha_entrega"));
                pedido.setEstado(resultSet.getString("estado"));
                pedido.setComentarios(resultSet.getString("comentarios"));
                pedido.setIdCliente(resultSet.getInt("id_cliente"));
                pedidosPorProducto.add(pedido);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error a listar pedidos del producto" + e.getMessage());
        }
        return pedidosPorProducto;
    }
}
