// Main para probar los metodos
package org.example.PATRON_DISENO_DAO;
import com.mysql.cj.util.EscapeTokenizer;
import org.example.PATRON_DISENO_DAO.servicios.ClienteServicio;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;
import org.example.PATRON_DISENO_DAO.entidades.Pedido;
import org.example.PATRON_DISENO_DAO.persistencia.PedidosDAO;
import org.example.PATRON_DISENO_DAO.servicios.PedidoServicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainServicios {
    public static void main(String[] args) {
        // verificandoClientes(); // Metodo para verificar clientes
        verificandoPedidos();
    }

    public static void verificandoClientes() {
        ClienteServicio cliente = new ClienteServicio();
        try {
            System.out.println("Verificando crear nuevo cliente....");
            Cliente clienteRecibido = cliente.crearNuevoCliente(12, "Goldfish Garden 2", "Alvaro N", "Goldfish", "9391241923", "4923139413", "Lima", "Lima", "Peru", "84203", 4, 240.42);
            System.out.println(clienteRecibido.toString());
            System.out.println("Cliente creado correctamente!");
            System.out.println("Verificando listar clientes...");
            List<Cliente> clientesRecibidos = cliente.listarClientes();
            System.out.println("Lista de clientes recibidos: ");
            for (Cliente clienteRecibidoPorLista : clientesRecibidos) {
                System.out.println(clienteRecibidoPorLista.toString());
            }
            System.out.println("Se han listado los clientes correctamente");
            int codigoClienteAListar = 456;
            System.out.println("Verificando clientes por codigo: " + codigoClienteAListar);
            List<Cliente> clientesPorCodigo = cliente.buscarClientesPorCodigo(codigoClienteAListar);
            System.out.println("Lista de clientes por codigo: " + codigoClienteAListar);
            for (Cliente clienteRecibidoPorCodigo : clientesPorCodigo) {
                System.out.println(clienteRecibidoPorCodigo.toString());
            }
            System.out.println("Se han listado los clientes por codigo correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verificandoPedidos() {
        PedidoServicio pedidoServicio = new PedidoServicio();
        System.out.println("Agregando pedido: ");
        try {
            String fechaPedidoCadena = "2008-05-13";
            String fechaEsperadaCadena = "2008-05-14";
            String fechaEntregaCadena = "2008-05-18";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date utilFechaPedido = sdf.parse(fechaPedidoCadena);
            Date utilFechaEsperada = sdf.parse(fechaEsperadaCadena);
            Date utilFechaEntrega = sdf.parse(fechaEntregaCadena);

            java.sql.Date sqlFechaPedido = new java.sql.Date(utilFechaPedido.getTime());
            java.sql.Date sqlFechaEsperada = new java.sql.Date(utilFechaEsperada.getTime());
            java.sql.Date sqlFechaEntrega = new java.sql.Date(utilFechaEntrega.getTime());

            Pedido pedidoAgregado = pedidoServicio.crearNuevoPedido(20,sqlFechaPedido,sqlFechaEsperada,sqlFechaEntrega, "Pendiente", "Pagado a plazos nuevamente", 9);
            System.out.println(pedidoAgregado.toString());
            System.out.println("Pedido creado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
