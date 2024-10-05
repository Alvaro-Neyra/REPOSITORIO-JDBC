package org.example.STATEMENT_CON_CLASES;
import java.lang.ClassNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        RecuperacionDeDatos datos = new RecuperacionDeDatos();

        try {
            // Buscar Clientes
            datos.buscarClientes();
            // Buscar cliente por codigo
            datos.buscarClientePorCodigo(14);
            // BuscarClientesPorEmpleado
            datos.buscarClientesPorEmpleado("Marcos");
            // Conseguir productos para reponer
            datos.getProductosParaReponer(40);
            // Conseguir productos gama
            datos.getProductosGama(4);
            // Conseguir pedidos por cliente
            datos.getPedidosPorCliente(10);
            // Consguir los pedidos por estaado dependienndo
            datos.getPedidosPorEstado("ENTREGADO");
            // Conseguir los producto por gama
            datos.getProductosPorGamaList();
            //Obtiene los productos no comprados
            datos.getProductosNoComprados();
            // Obtener un pedido por producto
            datos.getPedidosPorProducto(1);
            datos.getPedidosPorProducto(2);
            // Obtener pedidos entre fechas:
            datos.getPedidosPorFechas("2006-01-10", "2007-08-01");
            // Obtener productos por proveedor
            datos.getProductosPorProveedor("Frutales Talavera S.A");
            // Obtener pedidos por cliente cliente completo
            datos.getPedidosPorClienteCompleto(9);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
