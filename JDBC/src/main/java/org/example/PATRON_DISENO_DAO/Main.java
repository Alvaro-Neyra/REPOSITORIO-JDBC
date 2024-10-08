package org.example.PATRON_DISENO_DAO;

import org.example.PATRON_DISENO_DAO.persistencia.ClienteDAO;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;
import org.example.PATRON_DISENO_DAO.persistencia.ProductoDAO;
import org.example.PATRON_DISENO_DAO.entidades.Producto;
import org.example.PATRON_DISENO_DAO.persistencia.PedidosDAO;
import org.example.PATRON_DISENO_DAO.entidades.Pedido;
import org.example.PATRON_DISENO_DAO.persistencia.OficinaDAO;
import org.example.PATRON_DISENO_DAO.entidades.Oficina;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
        ProductoDAO pdao = new ProductoDAO();
        PedidosDAO pedao = new PedidosDAO();
        OficinaDAO ofDao = new OficinaDAO();
        // Creando dos entidades cliente
        int codigoCliente1 = 234;
        String nombreCliente1 = "Alvaro";
        String nombreContacto1 = "Juan";
        String apellidoContacto1 = "awdiajw";
        String telefonoContacto1 = "123456";
        String fax1 = "91994200921";
        String ciudad1 = "Lima";
        String region1 = "Lima";
        String pais1 = "Peru";
        String codigoPostal1 = "1934024";
        int idEmpleado1 = 13;
        double limiteCredito1 = 60000.32;
        Cliente nuevoCliente1 = new Cliente(codigoCliente1, nombreCliente1, nombreContacto1, apellidoContacto1, telefonoContacto1, fax1, ciudad1, region1, pais1, codigoPostal1, idEmpleado1, limiteCredito1);

        // Creando el segundo cliente
        int codigoCliente2 = 456;
        String nombreCliente2 = "Maria";
        String nombreContacto2 = "Luis";
        String apellidoContacto2 = "Perez";
        String telefonoContacto2 = "7891011";
        String fax2 = "9876543210";
        String ciudad2 = "Arequipa";
        String region2 = "Arequipa";
        String pais2 = "Peru";
        String codigoPostal2 = "04001";
        int idEmpleado2 = 15;
        double limiteCredito2 = 75000.50;
        Cliente nuevoCliente2 = new Cliente(codigoCliente2, nombreCliente2, nombreContacto2, apellidoContacto2, telefonoContacto2, fax2, ciudad2, region2, pais2, codigoPostal2, idEmpleado2, limiteCredito2);

        String codigoProducto = "5204120";
        String nombreProducto = "Audifonos";
        int idGamaProducto = 3;
        String dimensiones = "0,258";
        String proveedor = "Murcia Seasons";
        String descripcion = "Audifonos de alta calidad de color blanco";
        int stock = 30;
        double precioVenta = 15.00;
        double precioProveedor = 10.00;

        Producto nuevoProducto1 = new Producto(codigoProducto, nombreProducto, idGamaProducto, dimensiones, proveedor, descripcion, stock, precioVenta, precioProveedor);

        String codigoProducto2 = "6305211";
        String nombreProducto2 = "Teclado Mecánico";
        int idGamaProducto2 = 2;
        String dimensiones2 = "0,480";
        String proveedor2 = "TechGear Industries";
        String descripcion2 = "Teclado mecánico retroiluminado con switches azules";
        int stock2 = 50;
        double precioVenta2 = 45.99;
        double precioProveedor2 = 30.00;

        Producto nuevoProducto2 = new Producto(codigoProducto2, nombreProducto2, idGamaProducto2, dimensiones2, proveedor2, descripcion2, stock2, precioVenta2, precioProveedor2);

        try {
            System.out.println("Gestionando clientes: ");
            dao.guardarCliente(nuevoCliente1);
            dao.guardarCliente(nuevoCliente2);
            List<Cliente> clientesListados = dao.listarTodosLosClientes();
            for (Cliente cliente : clientesListados) {
                System.out.println(cliente.toString());
            }
            System.out.println("Buscando cliente por codigo: ");
            List<Cliente> clientesPorCodigo = dao.buscarClientePorCodigo(codigoCliente1);
            System.out.println("Lista de clientes por codigo: ");
            for (Cliente cliente : clientesPorCodigo) {
                System.out.println(cliente.toString());
            }
            dao.eliminarClientePorCodigo(codigoCliente1);
            List<Cliente> listarClientesPorEmpleado = dao.listarClientesPorEmpleado(11);
            System.out.println("Lista de clientes por empleado: ");
            for (Cliente cliente : listarClientesPorEmpleado) {
                System.out.println(cliente.toString());
            }
            dao.incrementarLimiteCredito();
            System.out.println("Gestionando productos: ");
            pdao.guardarProducto(nuevoProducto1);
            pdao.guardarProducto(nuevoProducto2);
            List<Producto> productosListados = pdao.listarProductos();
            for (Producto producto : productosListados) {
                System.out.println(producto.toString());
            }
            pdao.eliminarProductoPorCodigo("5204120"); // Eliminando este producto
            List<Producto> productosListados2 = pdao.listarProductos();
            for (Producto producto : productosListados2) {
                System.out.println(producto.toString());
            }
            List<Producto> productosBuscados = pdao.buscarProductoPorCodigo("FR-30");
            for (Producto producto : productosBuscados) {
                System.out.println(producto.toString());
            }
            List<Producto> productosPorProveedor = pdao.listarProductosPorProveedor("TechGear Industries");
            for (Producto producto : productosPorProveedor) {
                System.out.println(producto.toString());
            }
            pdao.modificarProducto(48, "Ciruelo SR", 20, 15);
            List<Producto> productosConLaMenorCantidadDeStockDisponible = pdao.listarProductoConLaMenorCantidadDeStockDisponible();
            System.out.println("Los productos con la menor cantidad de stock disponible son: ");
            for (Producto producto : productosConLaMenorCantidadDeStockDisponible) {
                System.out.println(producto.toString());
            }
            List<Producto> productosConMenorPrecioDeVenta = pdao.listarLosProductosConMenorPrecioVenta();
            System.out.println("Los productos con la menor precio de venta son: ");
            for (Producto producto : productosConMenorPrecioDeVenta) {
                System.out.println(producto.toString());
            }
            System.out.println("Gestionando pedidos: ");
            List<Pedido> pedidosPorCliente = pedao.listarPedidosPorCliente(5);
            for (Pedido pedido : pedidosPorCliente) {
                System.out.println(pedido.toString());
            }
            List<Pedido> pedidosEntregados = pedao.listarPedidosPorEstado("Entregado");
            for (Pedido pedido : pedidosEntregados) {
                System.out.println(pedido.toString());
            }
            List<Pedido> pedidosPorProducto = pedao.listarPedidosPorProducto(40);
            for (Pedido pedido : pedidosPorProducto) {
                System.out.println(pedido.toString());
            }
            System.out.println("Gestionando oficinas: ");
            List<Oficina> oficinasBuscadas = ofDao.buscarOficinaPorCodigo("MAD-ES");
            for (Oficina oficina : oficinasBuscadas) {
                System.out.println(oficina.toString());
            }
            ofDao.modificarOficina(5, "PAR-FR", "PARIS", "FRANCIA", "EMEA", "20394", "+33 203 012 123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
