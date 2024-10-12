package org.example.PATRON_DISENO_DAO;

import org.example.PATRON_DISENO_DAO.entidades.Pedido;
import org.example.PATRON_DISENO_DAO.entidades.Oficina;
import org.example.PATRON_DISENO_DAO.entidades.Cliente;
import org.example.PATRON_DISENO_DAO.entidades.Producto;
import org.example.PATRON_DISENO_DAO.servicios.ClienteServicio;
import org.example.PATRON_DISENO_DAO.servicios.OficinaServicio;
import org.example.PATRON_DISENO_DAO.servicios.PedidoServicio;
import org.example.PATRON_DISENO_DAO.servicios.ProductoServicio;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private ClienteServicio clienteServicio;
    private OficinaServicio oficinaServicio;
    private PedidoServicio pedidoServicio;
    private ProductoServicio productoServicio;

    public void iniciar() {
        clienteServicio = new ClienteServicio();
        oficinaServicio = new OficinaServicio();
        pedidoServicio = new PedidoServicio();
        productoServicio = new ProductoServicio();
        int opcion = -1;
        do {
            menuGeneral();
            opcion = pedirOpcion(1, 5, "Ingresa una opcion: ");
            elegirServicio(opcion);
        } while (opcion != 5);
    }

    private void menuGeneral() {
        System.out.println("\nMENU: Que deseas gestionar?");
        System.out.println("1. Clientes");
        System.out.println("2. Productos");
        System.out.println("3. Pedidos");
        System.out.println("4. Oficinas");
        System.out.println("5. Salir");
    }
    private void menuClientes() {
        System.out.println("\nMENU DE CLIENTES:");
        System.out.println("1. Crear nuevo cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Listar Clientes por codigo");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Listar Clientes por ID empleado");
        System.out.println("6. Incrementar por un 15% el limite de credito");
        System.out.println("7. Salir");
    }
    private void menuProductos() {
        System.out.println("\nMENU DE PRODUCTOS:");
        System.out.println("1. Crear nuevo producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Listar Productos por codigo");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Listar Productos por proveedor");
        System.out.println("6. Modificar Producto");
        System.out.println("7. Listar productos con el menor stock");
        System.out.println("8. Listar los productos con menor precio");
        System.out.println("9. Salir");
    }

    private void menuPedidos() {
        System.out.println("\nMENU DE PEDIDOS:");
        System.out.println("1. Crear nuevo pedido");
        System.out.println("2. Listar Pedidos por codigo");
        System.out.println("3. Listar Pedidos por cliente ID");
        System.out.println("4. Listar Pedidos por estado");
        System.out.println("5. Listar Pedidos por producto ID");
        System.out.println("6. Salir");
    }

    private void menuOficinas() {
        System.out.println("\nMENU DE OFICINAS:");
        System.out.println("1. Crear nuevo oficina");
        System.out.println("2. Listar Oficinas por su codigo");
        System.out.println("3. Modificar una oficina");
        System.out.println("4. Salir");
    }

    private int obtenerNumero(String mensaje) {
        int numero = -1;
        boolean error;
        do {
            error = false;
            try {
                System.out.println(mensaje);
                numero = sc.nextInt();
                if (numero < 0) {
                    System.out.println("No se aceptan números negativos");
                    error = true;
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.err.println("Valor inválido, solo se aceptan números enteros.");
                error = true;
            } catch (Exception e) {
                sc.nextLine();
                System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
                error = true;
            }
        } while (error);
        return numero;
    }

    private String obtenerCadena(String mensaje) {
        String cadenaAObtener = null;
        boolean error = false;
        do {
            error = false;
            try {
                do {
                    System.out.println(mensaje);
                    cadenaAObtener = sc.nextLine();
                    if (cadenaAObtener.equals("")) {
                        System.out.println("No pueden haber cadenas vacias");
                    }
                } while (cadenaAObtener.equals(""));
            } catch (Exception e) {
                error = true;
                System.out.println("Error al ingresar el cadena, intente de nuevo: " + e.getMessage());
                sc.nextLine();
            }
        } while (error);
        return cadenaAObtener;
    }

    private double obtenerDecimal(String mensaje) {
        double decimal = 0.0;
        boolean error;
        do {
            error = false;
            try {
                System.out.println(mensaje);
                decimal = sc.nextDouble();
                if (decimal < 0.00) {
                    System.out.println("No se aceptan números negativos.");
                    error = true;
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.err.println("Valor inválido, solo se aceptan números decimales.");
                error = true;
            } catch (Exception e) {
                sc.nextLine();
                System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
                error = true;
            }
        } while (error);
        return decimal;
    }

    private Date obtenerFecha(String mensaje) {
        Date fecha = null;
        boolean error;
        do {
            error = false;
            try {
                System.out.println(mensaje);
                String input = sc.next();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                formatoFecha.setLenient(false);

                java.util.Date fechaUtil = formatoFecha.parse(input);
                fecha = new Date(fechaUtil.getTime());

            } catch (ParseException e) {
                System.err.println("Formato de fecha inválido. El formato debe ser yyyy-MM-dd.");
                error = true;
            } catch (Exception e) {
                System.err.println("Error al ingresar la fecha, intente de nuevo: " + e.getMessage());
                error = true;
            }
        } while (error);

        return fecha;
    }


    private int pedirOpcion(int valorMinimo, int valorMaximo, String mensaje) {
        int opcion = -1;
        try {
            do {
                opcion = obtenerNumero(mensaje);
                if (opcion < valorMinimo || opcion > valorMaximo) {
                    System.out.println("Ingrese un numero valido!");
                }
            } while (opcion < valorMinimo || opcion > valorMaximo);
        } catch (Exception e) {
            System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
        }
        return opcion;
    }
    private void elegirServicio(int opcion) {
        int opcionServicio = -1;
        switch (opcion) {
            case 1:
                menuClientes();
                opcionServicio = pedirOpcion(1, 7, "Que deseas gestionar en clientes?: ");
                ejecutarClientes(opcionServicio);
                break;
            case 2:
                menuProductos();
                opcionServicio = pedirOpcion(1, 9, "Que deseas gestionar en productos?: ");
                ejecutarProductos(opcionServicio);
                break;
            case 3:
                menuPedidos();
                opcionServicio = pedirOpcion(1, 6, "Que deseas gestionar en pedidos?: ");
                ejecutarPedidos(opcionServicio);
                break;
            case 4:
                menuOficinas();
                opcionServicio = pedirOpcion(1, 4, "Que deseas gestionar en oficinas?: ");
                ejecutarOficinas(opcionServicio);
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private void ejecutarOficinas(int opcionServicio) {
        switch (opcionServicio) {
            case 1: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        Oficina nuevaOficina = new Oficina();
                        String codigoOficina = obtenerCadena("Ingresar codigo para la oficina: ");
                        nuevaOficina.setIdOficina(obtenerNumero("Ingresar un id para la oficina: "));
                        nuevaOficina.setCodigoOficina(codigoOficina);
                        nuevaOficina.setCiudad(obtenerCadena("Ingresar ciudad: "));
                        nuevaOficina.setPais(obtenerCadena("Ingresar pais: "));
                        nuevaOficina.setRegion(obtenerCadena("Ingresar region: "));
                        nuevaOficina.setCodigoPostal(obtenerCadena("Ingresar codigo postal: "));
                        nuevaOficina.setTelefono(obtenerCadena("Ingresar telefono: "));
                        if (!nuevaOficina.equals(oficinaServicio.buscarOficinaPorCodigo(codigoOficina).getFirst())) {
                            System.out.println("La oficina ha sido agregada correctamente");
                            System.out.println("La oficina agregada es: " + nuevaOficina.toString());
                        } else {
                            System.out.println("La oficina no ha sido agregada porque no existe");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al ingresar la oficina, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 2: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        String codigoOficina = obtenerCadena("Ingresar codigo para la oficina a buscar: ");
                        List<Oficina> oficinaPorCodigo = oficinaServicio.buscarOficinaPorCodigo(codigoOficina);
                        if (!oficinaPorCodigo.isEmpty()) {
                            System.out.println("La(s) oficina(s) encontradas por codigo--> cantidad: " + oficinaPorCodigo.size());
                            for (Oficina oficina : oficinaPorCodigo) {
                                System.out.println(oficina.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al buscar oficina, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 3: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        Oficina oficinaAModificar = new Oficina();
                        oficinaAModificar.setCodigoOficina(obtenerCadena("Ingrese el codigo de la oficina: "));
                        if (oficinaServicio.verificarOficinaRepetida(oficinaAModificar.getCodigoOficina())) {
                            oficinaAModificar.setCiudad(obtenerCadena("Ingresar ciudad: "));
                            oficinaAModificar.setPais(obtenerCadena("Ingresar pais: "));
                            oficinaAModificar.setRegion(obtenerCadena("Ingresar region: "));
                            oficinaAModificar.setCodigoPostal(obtenerCadena("Ingresar codigo postal: "));
                            oficinaAModificar.setTelefono(obtenerCadena("Ingresar telefono: "));
                            oficinaServicio.modificarOficina(oficinaAModificar);
                        } else {
                            System.out.println("Oficina no encontrada, no se puede modificar!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al modificar oficina, intentelo de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 4: {
                System.out.println("Saliendo..");
                break;
            }
            default: {
                System.out.println("Opcion invalida, intente de nuevo");
                break;
            }
        }
    }

    private void ejecutarPedidos(int opcionServicio) {
        switch (opcionServicio) {
            case 1: {
                boolean error = false;
                try {
                    int codigoPedido = obtenerNumero("Ingrese el codigo del pedido: ");
                    Date fechaPedido = obtenerFecha("Ingrese la fecha de pedido: ");
                    Date fechaEsperada = obtenerFecha("Ingrese la fecha de esperada: ");
                    Date fechaEntrega = obtenerFecha("Ingrese la fecha de entrega: ");
                    String estado = obtenerCadena("Ingrese el estado: ");
                    String comentarios = obtenerCadena("Ingrese el comentarios: ");
                    int idCliente = obtenerNumero("Ingrese el id del cliente: ");
                    Pedido pedidoAgregado = new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente);
                    if (!pedidoAgregado.equals(pedidoServicio.buscarPedidoPorCodigo(codigoPedido).getFirst())) {
                        System.out.println("El pedido agregado correctamente");
                        System.out.println("El pedido agregado es: " + pedidoAgregado.toString());
                    } else {
                        System.out.println("El pedido no agregado porque ya existe");
                    }
                } catch (Exception e) {
                    System.err.println("Error al ingresar el pedido, intente de nuevo: " + e.getMessage());
                    error = true;
                    sc.nextLine();
                }
                break;
            }
            case 2: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int codigoPedido = obtenerNumero("Ingrese el codigo del pedido: ");
                        List<Pedido> pedidosBuscados = pedidoServicio.buscarPedidoPorCodigo(codigoPedido);
                        if (!pedidosBuscados.isEmpty()) {
                            System.out.println("Pedido encontrado!!: " + pedidosBuscados.getFirst().toString());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al buscar pedido por codigo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 3: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int idCliente = obtenerNumero("Ingrese el id del cliente: ");
                        List<Pedido> listarPedidosPorCliente = pedidoServicio.listarPedidosPorClienteID(idCliente);
                        if (!listarPedidosPorCliente.isEmpty()) {
                            System.out.println("Pedido encontrado!!: " + listarPedidosPorCliente.getFirst().toString());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar pedidos por cliente ID: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 4: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        String estado = obtenerCadena("Ingrese el estado ('Entregado', 'Pendiente', 'Rechazado'): ");
                        List<Pedido> pedidosPorEstado = pedidoServicio.listarPedidosPorEstado(estado);
                        if (!pedidosPorEstado.isEmpty()) {
                            System.out.println("Pedido encontrado!!: " + pedidosPorEstado.getFirst().toString());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar pedidos por estado, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 5: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int idProducto = obtenerNumero("Ingrese el id del producto: ");
                        List<Pedido> pedidosPorProductoID = pedidoServicio.listarPedidosPorProductoID(idProducto);
                        if (!pedidosPorProductoID.isEmpty()) {
                            System.out.println("Pedido encontrado!!: " + pedidosPorProductoID.getFirst().toString());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar pedidos por producto ID, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 6: {
                System.out.println("Saliendo...");
                break;
            }
            default: {
                System.out.println("Opcion invalida, intente de nuevo");
                break;
            }
        }
    }

    private void ejecutarProductos(int opcionServicio) {
        switch (opcionServicio) {
            case 1: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        Producto nuevoProducto = new Producto();
                        nuevoProducto.setCodigoProducto(obtenerCadena("Ingrese el codigo del producto: "));
                        nuevoProducto.setNombreProducto(obtenerCadena("Ingrese el nombre de producto: "));
                        nuevoProducto.setIdGamaProducto(obtenerNumero("Ingrese el id del gama: "));
                        nuevoProducto.setDimensiones(obtenerCadena("Ingrese las dimensiones: "));
                        nuevoProducto.setProveedor(obtenerCadena("Ingrese el proveedor: "));
                        nuevoProducto.setDescripcion(obtenerCadena("Ingrese la descripcion: "));
                        nuevoProducto.setStock(obtenerNumero("Ingrese el stock: "));
                        nuevoProducto.setPrecioVenta(obtenerDecimal("Ingrese el precio de venta: "));
                        nuevoProducto.setPrecioProveedor(obtenerDecimal("Ingrese el precio del proveedor: "));
                        Producto productoAgregado = productoServicio.agregarProducto(nuevoProducto);
                        if (!productoAgregado.equals(nuevoProducto)) {
                            System.out.println("Producto agregado correctamente!");
                            System.out.println(productoAgregado.toString());
                        } else {
                            System.out.println("Producto no agregado, porque ya existe");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 2: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        List<Producto> productosRecibidos = productoServicio.listarProductos();
                        if (!productosRecibidos.isEmpty()) {
                            System.out.println("Productos recibidos!! ---> cantidad: " + productosRecibidos.size());
                            for (Producto productoRecibido: productosRecibidos) {
                                System.out.println(productoRecibido.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar los productos, intente de nuevo:" + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 3: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        String codigoABuscar = obtenerCadena("Ingrese el codigo del producto a buscar: ");
                        List<Producto> productosConCodigoEncontrados = productoServicio.buscarProductoPorCodigo(codigoABuscar);
                        if (!productosConCodigoEncontrados.isEmpty()) {
                            System.out.println("Productos(s) encontrado(s)!!: " + productosConCodigoEncontrados.size());
                            for (Producto productoEncontrado: productosConCodigoEncontrados) {
                                System.out.println(productoEncontrado.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al buscar producto por codigo, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 4: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        String codigoAEliminar = obtenerCadena("Ingrese el codigo del Producto a eliminar: ");
                        productoServicio.eliminarProductoPorCodigo(codigoAEliminar);
                        System.out.println("Productos(s) eliminado(s)!. Cantidad de productos actuales: " + productoServicio.listarProductos().size());
                    } catch (Exception e) {
                        System.err.println("Error al eliminar el producto!: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 5: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        String proveedor = obtenerCadena("Ingrese el proveedor del producto: ");
                        List<Producto> productosPorProveedor = productoServicio.productosPorProveedor(proveedor);
                        if (!productosPorProveedor.isEmpty()) {
                            System.out.println("Productos(s) encontrado(s)!!: " + productosPorProveedor.size());
                            for (Producto productoPorProveedor: productosPorProveedor) {
                                System.out.println(productoPorProveedor.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar producto por proveedor, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 6: {
                boolean error = false;
                do {
                    try {
                        Producto productoAModificar = new Producto();
                        productoAModificar.setCodigoProducto(obtenerCadena("Ingrese el codigo del producto: "));
                        if (productoServicio.verificarProductoRepetido(productoAModificar.getCodigoProducto())) {
                            productoAModificar.setNombreProducto(obtenerCadena("Ingrese el nombre de producto: "));
                            productoAModificar.setIdGamaProducto(obtenerNumero("Ingrese el id del gama: "));
                            productoAModificar.setDimensiones(obtenerCadena("Ingrese las dimensiones: "));
                            productoAModificar.setProveedor(obtenerCadena("Ingrese el proveedor: "));
                            productoAModificar.setDescripcion(obtenerCadena("Ingrese la descripcion: "));
                            productoAModificar.setStock(obtenerNumero("Ingrese el stock: "));
                            productoAModificar.setPrecioVenta(obtenerDecimal("Ingrese el precio de venta: "));
                            productoAModificar.setPrecioProveedor(obtenerDecimal("Ingrese el precio del proveedor: "));
                            productoServicio.modificarProducto(productoAModificar);
                        } else {
                            System.out.println("Producto no encontrado, no se puede modificar!");
                        }
                    } catch (Exception e) {
                        System.err.println("Error al modificar el producto, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 7: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        List<Producto> productoConElMenorStock = productoServicio.listarProductoConElMenorStock();
                        if (!productoConElMenorStock.isEmpty()) {
                            System.out.println("Producto con el menor stock recibidos!!" + productoConElMenorStock.size());
                            for (Producto producto: productoConElMenorStock) {
                                System.out.println(producto.toString());
                            }
                        }
                    } catch(Exception e) {
                        System.err.println("Error al modificar el producto, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 8: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        List<Producto> productosConElMenorPrecio = productoServicio.listarLosProductosConMenorPrecio();
                        if (!productosConElMenorPrecio.isEmpty()) {
                            System.out.println("Productos con el menor precio recibidos!!" + productosConElMenorPrecio.size());
                            for (Producto producto: productosConElMenorPrecio) {
                                System.out.println(producto.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error al listar los productos con menor precio: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 9: {
                System.out.println("Saliendo...");
                break;
            }
            default: {
                System.out.println("Opcion no valida");
                break;
            }
        }
    }

    private void ejecutarClientes(int opcion) {
        switch(opcion) {
            case 1: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int codigoCliente = obtenerNumero("Ingrese el codigo del Cliente: ");
                        sc.nextLine();
                        String nombreCliente = obtenerCadena("Ingrese el nombre del Cliente: ");
                        String nombreContacto = obtenerCadena("Ingrese el nombre del contacto: ");
                        String apellidoContacto = obtenerCadena("Ingrese el apellido del contacto: ");
                        String telefono = obtenerCadena("Ingrese el telefono: ");
                        String fax = obtenerCadena("Ingrese el fax: ");
                        String ciudad = obtenerCadena("Ingrese la ciudad: ");
                        String region = obtenerCadena("Ingrese la region: ");
                        String pais = obtenerCadena("Ingrese el pais: ");
                        String codigoPostal = obtenerCadena("Ingrese el codigo de postal: ");
                        int idEmpleado = obtenerNumero("Ingrese el id del empleado: ");
                        double limiteCredito = obtenerDecimal("Ingrese el limite de credito: ");
                        clienteServicio.crearNuevoCliente(codigoCliente, nombreCliente, nombreContacto, apellidoContacto, telefono, fax, ciudad, region, pais, codigoPostal, idEmpleado, limiteCredito);
                    } catch (Exception e) {
                        System.err.println("Error al ingresar el dato, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 2: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        List<Cliente> clientesRecibidos = clienteServicio.listarClientes();
                        if (!clientesRecibidos.isEmpty()) {
                            System.out.println("Clientes recibidos!! ---> cantidad: " + clientesRecibidos.size());
                            for (Cliente clienteRecibido: clientesRecibidos) {
                                System.out.println(clienteRecibido.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar los clientes, intente de nuevo:" + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 3: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int codigoABuscar = obtenerNumero("Ingrese el codigo del Cliente a buscar: ");
                        List<Cliente> clientesConCodigoEncontrados = clienteServicio.buscarClientesPorCodigo(codigoABuscar);
                        if (!clientesConCodigoEncontrados.isEmpty()) {
                            System.out.println("Cliente(s) encontrado(s)!!: " + clientesConCodigoEncontrados.size());
                            for (Cliente clienteEncontrado: clientesConCodigoEncontrados) {
                                System.out.println(clienteEncontrado.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al buscar cliente por codigo, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 4: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int codigoAEliminar = obtenerNumero("Ingrese el codigo del Cliente a eliminar: ");
                        clienteServicio.eliminarCliente(codigoAEliminar);
                        System.out.println("Cliente(s) eliminado(s)!. Cantidad de clientes actuales: " + clienteServicio.listarClientes().size());
                    } catch (Exception e) {
                        System.err.println("Error al eliminar el cliente!: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 5: {
                boolean error = false;
                do {
                    error = false;
                    try {
                        int idEmpleado = obtenerNumero("Ingrese el id del empleado: ");
                        List<Cliente> clientesPorIDEmpleado = clienteServicio.listarClientePorIdEmpleado(idEmpleado);
                        if (!clientesPorIDEmpleado.isEmpty()) {
                            System.out.println("Cliente(s) encontrado(s)!!: " + clientesPorIDEmpleado.size());
                            for (Cliente clienteEncontrado: clientesPorIDEmpleado) {
                                System.out.println(clienteEncontrado.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar cliente por id empleado, intente de nuevo: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 6: {
                boolean error = false;
                do {
                    try {
                        clienteServicio.incrementarLimiteCreditoClientes();
                    } catch (Exception e) {
                        System.err.println("Error al incrementar Limite de credito a los clientes: " + e.getMessage());
                        error = true;
                        sc.nextLine();
                    }
                } while (error);
                break;
            }
            case 7: {
                System.out.println("Saliendo...");
                break;
            }
            default:
                break;
        }
    }
}
