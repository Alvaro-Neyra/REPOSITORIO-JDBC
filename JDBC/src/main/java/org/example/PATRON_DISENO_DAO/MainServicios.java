// Main para probar los metodos
package org.example.PATRON_DISENO_DAO;
import com.mysql.cj.util.EscapeTokenizer;
import org.example.PATRON_DISENO_DAO.entidades.Oficina;
import org.example.PATRON_DISENO_DAO.entidades.Producto;
import org.example.PATRON_DISENO_DAO.servicios.ClienteServicio;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;
import org.example.PATRON_DISENO_DAO.entidades.Pedido;
import org.example.PATRON_DISENO_DAO.persistencia.PedidosDAO;
import org.example.PATRON_DISENO_DAO.servicios.OficinaServicio;
import org.example.PATRON_DISENO_DAO.servicios.PedidoServicio;
import org.example.PATRON_DISENO_DAO.servicios.ProductoServicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainServicios {
    public static void main(String[] args) {
//        verificandoClientes(); // Metodo para verificar el servicio para clientes
//        verificandoPedidos(); // Metodo para verificar el servicio para pedidos
//        verificandoProductos(); // Metodo para verificar el servicio para productos
        verificandoOficinas(); // Metodo para verificar el servicio para oficinas
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
            System.out.println("Eliminando un cliente por codigo: ");
            cliente.eliminarCliente(codigoClienteAListar);
            System.out.println("Cliente eliminado correctamente!");
            System.out.println("Listar clientes por empleado ID: ");
//            cliente.listarClientePorIdEmpleado(0); // Da error ya que no permite codigo 0 o menor a 0
            cliente.listarClientePorIdEmpleado(14);
            System.out.println("Incrementar el limite de credito de los clientes por un 15%:");
            cliente.incrementarLimiteCreditoClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verificandoPedidos() {
        PedidoServicio pedidoServicio = new PedidoServicio();
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

            System.out.println("Verificando Agregando pedido: ");
            Pedido pedidoAgregado = pedidoServicio.crearNuevoPedido(20,sqlFechaPedido,sqlFechaEsperada,sqlFechaEntrega, "Pendiente", "Pagado a plazos nuevamente", 9);
            System.out.println(pedidoAgregado.toString());
            System.out.println("Pedido creado correctamente!");
            int idClienteAUsar = 3;
            System.out.println("Verificando Listar pedidos por ID Cliente: " + idClienteAUsar);
            System.out.println("Lista de pedidos por ID Cliente: " + idClienteAUsar);
            List<Pedido> pedidosPorCliente = pedidoServicio.listarPedidosPorClienteID(idClienteAUsar);
            for (Pedido pedido : pedidosPorCliente) {
                System.out.println(pedido.toString());
            }
//            pedidoServicio.listarPedidosPorClienteID(0); // Da error ya que seria un idcliente invalido
            String estadoDePedidoAListar = "Rechazado";
            System.out.println("Verificando Listar pedidos por estado: " + estadoDePedidoAListar);
            System.out.println("Lista de pedidos por estado: " + estadoDePedidoAListar);
            List<Pedido> pedidosPorEstado = pedidoServicio.listarPedidosPorEstado(estadoDePedidoAListar);
            for (Pedido pedido : pedidosPorEstado) {
                System.out.println(pedido.toString());
            }
//            pedidoServicio.listarPedidosPorEstado(""); // Da error ya que no permite cadena vacia o nula
            int idProducto = 4;
            System.out.println("Verificando Listar pedidos por ID Producto: " + idProducto);
            System.out.println("Lista de pedidos por ID Producto: " + idProducto);
            List<Pedido> pedidosPorIdProducto = pedidoServicio.listarPedidosPorProductoID(idProducto);
            for (Pedido pedido : pedidosPorIdProducto) {
                System.out.println(pedido.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void verificandoProductos() {
        ProductoServicio productoServicio = new ProductoServicio();
        try {
            System.out.println("Verificando agregando productos: ");
            // Verficando Agregar producto
            Producto nuevoProducto = new Producto(
                    "P001",
                    "Laptop X200",
                    2,
                    "30x20x2 cm",
                    "ProveedorTech",
                    "Laptop de alto rendimiento con 16GB RAM y SSD de 512GB",
                    50,
                    1200.99,
                    800.50
            );
            Producto productoAgregado = productoServicio.agregarProducto(nuevoProducto);
            System.out.println(productoAgregado.toString());
            System.out.println("Producto creado correctamente!");
            // Agregando un producto existente
            System.out.println("Verificando un producto existente!");
            Producto productoNuevo = productoServicio.agregarProducto(new Producto("AR-004", "Carlos", 3, "15-20", "Murcia Stores", "Es un producto que nos ayuda a mejorar el medio ambiente",
                    20, 145.05, 45.40));
            productoNuevo.toString();
            // Listando todos los productos
            System.out.println("Verificando listando productos");
            List<Producto> productosListados = productoServicio.listarProductos();
            for (Producto producto : productosListados) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han listado los productos correctamente!");
            System.out.println("Verificando Buscando un producto por codigo!");
            List<Producto> productosEncontrados = productoServicio.buscarProductoPorCodigo("P001");
            for (Producto producto : productosEncontrados) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han encontrado los productos correctamente!");
            System.out.println("Verificando eliminando un producto por codigo");
            productoServicio.eliminarProductoPorCodigo("P001");
            System.out.println("Producto eliminado correctamente");
            System.out.println("Verificando listar los productos por proveedor");
            List<Producto> productoPorProveedor = productoServicio.productosPorProveedor("HiperGarden Tools");
            for (Producto producto : productoPorProveedor) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han encontrado los productos correctamente");
//            List<Producto> productosPorProveedorFallido = productoServicio.productosPorProveedor(""); // Dara error debido a que el metodo no acepta strings vacios
            System.out.println("Verificando modificar el producto");
            Producto productoModificado = new Producto(
                    "P001",
                    "Laptop X300",
                    102,
                    "32x22x2 cm",
                    "ProveedorPro",
                    "Laptop de alto rendimiento con 32GB RAM y SSD de 1TB",
                    45,
                    1400.99,
                    900.75
            );
            productoServicio.modificarProducto(productoModificado);
            System.out.println("Producto modificado correctamente");
            List<Producto> productoModificadoRecibido = productoServicio.buscarProductoPorCodigo("P001");
            for (Producto producto : productoModificadoRecibido) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han encontrado los productos modificados correctamente");
            System.out.println("Verificar el listado de productos con menor stock");
            List<Producto> productosConMenorStock = productoServicio.listarProductoConElMenorStock();
            for (Producto producto : productosConMenorStock) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han encontrado los productos con el menor stock");
            System.out.println("Verificar el listado de los productos con el menor precio");
            List<Producto> productosConElMenorPrecio = productoServicio.listarLosProductosConMenorPrecio();
            for (Producto producto : productosConElMenorPrecio) {
                System.out.println(producto.toString());
            }
            System.out.println("Se han listado los productos con el menor precio");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void verificandoOficinas() {
        OficinaServicio oficinaServicio = new OficinaServicio();
        try {
            System.out.println("Verificando agregar una oficina: ");
            Oficina oficina = new Oficina("OF123", "Lima", "Perú", "Sur", "15001", "+51 123 456 789");
            oficinaServicio.agregarOficina(oficina);
            System.out.println("Se agrego la oficina correctamente!");
            System.out.print("Agregar una oficina ya existente: ");
            Oficina oficinaExistente = oficinaServicio.agregarOficina(oficina);
            System.out.println(oficinaExistente.toString());
            System.out.println("Verificando buscar la(s) oficina(s) por codigo: ");
            List<Oficina> oficinasEncontradas = oficinaServicio.buscarOficinaPorCodigo("BOS-USA");
            for (Oficina oficinaEncontrada : oficinasEncontradas) {
                System.out.println(oficinaEncontrada.toString());
            }
            System.out.println("Las oficinas encontradas se han listado correctamente");
            System.out.println("Verificando modificar oficina");
            // Modificar oficina que no existe en la base de datos:
//            oficinaServicio.modificarOficina(new Oficina("10AD4", "Lima", "Perú", "Sur", "16001", "+51 123 456 789"));
            // Modificar una oficina existente:
            oficinaServicio.modificarOficina(new Oficina("OF123", "Lima", "Perú", "Norte", "15001", "+51 123 456 789"));
            System.out.println("Se modifico la oficina correctamente");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
