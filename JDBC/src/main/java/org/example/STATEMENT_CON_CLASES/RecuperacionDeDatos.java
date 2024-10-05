package org.example.STATEMENT_CON_CLASES;
import java.sql.*;

public class RecuperacionDeDatos {
    private ConexionBD conexionBD= new ConexionBD();

    public void buscarClientes() throws SQLException,ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente";
        try {
            Connection conexion = conexionBD.conectar();
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            int count = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " - " + apellido + " - " + telefono);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los clientes: " + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexion: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexion: " + e.getMessage());
                }

            }
        }
    }

    // Usando try-with-resources
    public void buscarClientePorCodigo(int codigo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = ?";

        try (Connection conexion = conexionBD.conectar()) {
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setInt(1, codigo);

                try (ResultSet rs = pstmt.executeQuery()) {
                    int count = 0;

                    while (rs.next()) {
                        String nombre = rs.getString("nombre_contacto");
                        String apellido = rs.getString("apellido_contacto");
                        String ciudad = rs.getString("ciudad");
                        String pais = rs.getString("pais");
                        double limite_credito = rs.getDouble("limite_credito");
                        count++;
                        System.out.println("Cliente: \n" +
                                "Nombre: " + nombre + "\n" +
                                "Apellido: " + apellido + "\n" +
                                "Ciudad: " + ciudad + "\n" +
                                "Pais: " + pais + "\n" +
                                "Limite de Credito: " + limite_credito + "\n"
                        );
                    }

                    if (count == 0) {
                        System.out.println("No se encontró ningún cliente con el código: " + codigo);
                    }
                } catch (SQLException e) {
                    throw new SQLException("Error al obtener los clientes: " + e.getMessage());
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los clientes: " + e.getMessage());
            }
        }
    }

    public void buscarClientesPorEmpleado(String empleado) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT '*' FROM cliente `cli` JOIN empleado `emp` ON cli.id_empleado = emp.id_empleado WHERE emp.nombre = ?";

        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setString(1, empleado);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int codigoCliente = rs.getInt("codigo_cliente");
                    String nombre = rs.getString("nombre_contacto");
                    String apellido = rs.getString("apellido_contacto");
                    String telefono = rs.getString("telefono");
                    String ciudad = rs.getString("ciudad");
                    String pais = rs.getString("pais");
                    double limiteCredito = rs.getDouble("limite_credito");
                    count++;
                    System.out.println("Cliente: " + count + ": ");
                    System.out.println("Codigo cliente: " + codigoCliente);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Apellido: " + apellido);
                    System.out.println("Telefono: " + telefono);
                    System.out.println("Ciudad: " + ciudad);
                    System.out.println("Pais: " + pais);
                    System.out.println("Limite de Credito: " + limiteCredito);
                    System.out.println("-------------------");
                }

                if (count == 0) {
                    System.out.println("No se encontro ningun cliente con el empleado: " + empleado);
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los clientes: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los clientes: " + e.getMessage());
        }
    }

    public void getProductosParaReponer(int puntoReposicion) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT DISTINCT prod.id_producto, prod.codigo_producto, prod.nombre, SUM(prod.cantidad_en_stock) `cantidad_total` FROM producto `prod` WHERE prod.cantidad_en_stock < ? GROUP BY prod.nombre, prod.id_producto, prod.codigo_producto";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, puntoReposicion);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int idProducto = rs.getInt("id_producto");
                    String codigoProducto = rs.getString("codigo_producto");
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad_total");
                    count++;
                    System.out.println(count + " - " + idProducto + " - " + codigoProducto + " - " + nombre + " - " + cantidad);
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los productos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getProductosGama(int idGama) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT DISTINCT prod.codigo_producto, prod.nombre, prod.dimensiones, prod.proveedor, prod.descripcion, SUM(prod.cantidad_en_stock) AS cantidad_en_stock_total, prod.precio_venta, prod.precio_proveedor, gam.id_gama, gam.gama FROM producto `prod` " +
                "JOIN gama_producto `gam` ON prod.id_gama = gam.id_gama " +
                "WHERE gam.id_gama = ? " +
                "GROUP BY prod.codigo_producto, nombre, dimensiones, proveedor, descripcion, precio_venta, precio_proveedor, id_gama";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, idGama);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    String codigoProducto = rs.getString("codigo_producto");
                    String nombreProducto = rs.getString("nombre");
                    String dimensiones = rs.getString("dimensiones");
                    String proveedor = rs.getString("proveedor");
                    String descripcion = rs.getString("descripcion");
                    int cantidadEnStock = rs.getInt("cantidad_en_stock_total");
                    double precioVenta = rs.getDouble("precio_venta");
                    double precioProveedor = rs.getDouble("precio_proveedor");
                    int idGamaProduct = rs.getInt("id_gama");
                    String gama = rs.getString("gama");
                    count++;
                    System.out.println("Fila: " + count + "-" + codigoProducto + "-" + nombreProducto + "-" + dimensiones + "-" + proveedor + '-' + descripcion + '-' + cantidadEnStock + "-" + precioVenta + "-" + precioProveedor+ "-" + idGamaProduct + "-" + gama);
                }
            }
            catch (SQLException e) {
                throw new SQLException("Error al obtener los productos: " + e.getMessage());
            }
        }
        catch (SQLException e) {
            throw new SQLException("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getPedidosPorCliente(int idCliente) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ped.id_pedido, ped.fecha_pedido FROM pedido `ped` JOIN cliente `cli` ON ped.id_cliente = cli.id_cliente WHERE cli.id_cliente = ?";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int idPedido = rs.getInt("id_pedido");
                    String fechaPedido = rs.getString("fecha_pedido");
                    String comentarios = rs.getString("comentarios");
                    System.out.println("Fila: " + count + "-" + idPedido + "-" + fechaPedido + "-" + comentarios);
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    public void getPedidosPorEstado(String pedido) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM pedido WHERE estado = ?";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setString(1, pedido);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int idPedido = rs.getInt("id_pedido");
                    int codigoPedido = rs.getInt("codigo_pedido");
                    String fechaPedido = rs.getString("fecha_pedido");
                    String fechaEsperada = rs.getString("fecha_esperada");
                    String fechaEntrega = rs.getString("fecha_entrega");
                    String estadoPedido = rs.getString("estado");
                    String comentarios = rs.getString("comentarios");
                    count++;
                    System.out.println("Fila: " + count + "-" + idPedido + "-" + codigoPedido + "-" + fechaPedido + "-" + fechaEsperada + "-" + fechaEntrega + "-" + estadoPedido + "-" + comentarios);
                }
            }
            catch (SQLException e) {
                throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    public void getProductosPorGamaList() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT COUNT(*) AS cantidad, id_gama FROM producto GROUP BY id_gama";
        try (Connection conexion = conexionBD.conectar(); Statement stmt = conexion.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sqlQuery)) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println("Cantidad " + rs.getInt("cantidad") + "- Id Gama " + rs.getString("id_gama"));
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getProductosNoComprados() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT p.id_producto, p.nombre FROM producto `p` " +
                "LEFT JOIN detalle_pedido `dp` ON p.id_producto = dp.id_producto " +
                "WHERE dp.id_producto IS NULL";
        try (Connection conexion = conexionBD.conectar(); Statement stmt = conexion.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sqlQuery)) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println("Producto No comprado: " + rs.getInt("id_producto") + "-" + rs.getString("nombre"));
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los productos: " + e.getMessage());
            }
        } catch(SQLException e) {
            throw new SQLException("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getPedidosPorProducto(int idProducto) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT prod.id_producto, ped.id_pedido, ped.codigo_pedido, ped.fecha_pedido, ped.fecha_entrega, ped.estado, ped.comentarios, ped.id_cliente " +
                "FROM pedido ped " +
                "INNER JOIN detalle_pedido dp ON dp.id_pedido = ped.id_pedido " +
                "INNER JOIN producto prod ON dp.id_producto = prod.id_producto " +
                "WHERE prod.id_producto = ?";

        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setInt(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println("Pedido " + count + ": " +
                            "ID Producto: " + rs.getInt("id_producto") + ", " +
                            "ID Pedido: " + rs.getInt("id_pedido") + ", " +
                            "Código Pedido: " + rs.getString("codigo_pedido") + ", " +
                            "Fecha Pedido: " + rs.getDate("fecha_pedido") + ", " +
                            "Fecha Entrega: " + rs.getDate("fecha_entrega") + ", " +
                            "Estado: " + rs.getString("estado") + ", " +
                            "Comentarios: " + rs.getString("comentarios") + ", " +
                            "ID Cliente: " + rs.getInt("id_cliente"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    public void getPedidosPorFechas(String desdeFecha, String hastaFecha) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM pedido WHERE fecha_entrega BETWEEN ? AND ?";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement stmt = conexion.prepareStatement(sqlQuery)) {
            stmt.setString(1, desdeFecha);
            stmt.setString(2, hastaFecha);
            try (ResultSet rs = stmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println("Pedido: " + count + ": " +
                            "ID Pedido: " + rs.getInt("id_pedido") + ", " +
                            "Código Pedido: " + rs.getString("codigo_pedido") + ", " +
                            "Fecha Pedido: " + rs.getDate("fecha_pedido") + ", " +
                            "Fecha Esperada: " + rs.getDate("fecha_esperada") + ", " +
                            "Fecha Entrega: " + rs.getDate("fecha_entrega") + ", " +
                            "Estado: " + rs.getString("estado") + ", " +
                            "Comentarios: " + rs.getString("comentarios")
                    );
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    public void getProductosPorProveedor(String nombreProveedor) throws SQLException, ClassNotFoundException {
        String sqlQuery = " SELECT id_producto, nombre FROM producto WHERE proveedor = ?";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sqlQuery)) {
            pstmt.setString(1, nombreProveedor);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    count++;
                    System.out.println("Producto: " + "-" + rs.getInt("id_producto") + "-" + rs.getString("nombre"));
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los productos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los productos: " + e.getMessage());
        }
    }

    public void getPedidosPorClienteCompleto(int idCliente) throws SQLException, ClassNotFoundException {
        String sql = "SELECT cli.nombre_cliente, ped.id_pedido, ped.codigo_pedido, ped.fecha_pedido, ped.fecha_esperada, ped.fecha_entrega, ped.estado, ped.comentarios FROM pedido `ped` JOIN cliente `cli` ON ped.id_cliente = cli.id_cliente WHERE cli.id_cliente = ?";
        try (Connection conexion = conexionBD.conectar(); PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    String nombreCliente = rs.getString("cli.nombre_cliente");
                    int idPedido = rs.getInt("id_pedido");
                    int codigoPedido = rs.getInt("codigo_pedido");
                    String fechaPedido = rs.getString("fecha_pedido");
                    String fechaEsperada = rs.getString("fecha_esperada");
                    String fechaEntrega = rs.getString("fecha_entrega");
                    String estadoPedido = rs.getString("estado");
                    String comentarios = rs.getString("comentarios");
                    count++;
                    System.out.println("Fila: " + count + "-" + nombreCliente + "-" + idPedido + "-" + codigoPedido + "-" + fechaPedido + "-" + fechaEsperada + "-" + fechaEntrega + "-" + estadoPedido + "-" + comentarios);
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los pedidos: " + e.getMessage());
        }
    }
}
