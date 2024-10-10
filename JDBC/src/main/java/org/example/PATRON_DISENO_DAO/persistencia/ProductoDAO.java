package org.example.PATRON_DISENO_DAO.persistencia;


import org.example.PATRON_DISENO_DAO.entidades.Producto;
import java.util.List;
import java.util.ArrayList;

public class ProductoDAO extends DAO{
    public void guardarProducto(Producto producto) throws Exception{
        if (producto == null) {
            throw new Exception("Producto no puede ser nulo");
        }

        try {
            String sql = "INSERT INTO producto (codigo_producto, nombre, id_gama, dimensiones, proveedor, descripcion, cantidad_en_stock, precio_venta, precio_proveedor) " +
                    "VALUES (" + producto.getCodigoProducto() + ", '" + producto.getNombreProducto() + "', " + producto.getIdGamaProducto() + ", '" + producto.getDimensiones() + "', '" +
                    producto.getProveedor() + "', '" + producto.getDescripcion() + "', " + producto.getStock() + ", " + producto.getPrecioVenta() + ", " + producto.getPrecioProveedor() + ")";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> listarProductos() throws Exception{
        List<Producto> productosListados = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto";
            consultarBase(sql);

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(resultSet.getString("codigo_producto"));
                producto.setNombreProducto(resultSet.getString("nombre"));
                producto.setIdGamaProducto(resultSet.getInt("id_gama"));
                producto.setDimensiones(resultSet.getString("dimensiones"));
                producto.setProveedor(resultSet.getString("proveedor"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setStock(resultSet.getInt("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
                productosListados.add(producto);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            e.printStackTrace();
        }
        return productosListados;
    }

    public List<Producto> buscarProductoPorCodigo(String codigoProducto) throws Exception {
        List<Producto> productosPorCodigo = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto WHERE codigo_producto = '" + codigoProducto + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(resultSet.getString("codigo_producto"));
                producto.setNombreProducto(resultSet.getString("nombre"));
                producto.setIdGamaProducto(resultSet.getInt("id_gama"));
                producto.setDimensiones(resultSet.getString("dimensiones"));
                producto.setProveedor(resultSet.getString("proveedor"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setStock(resultSet.getInt("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
                productosPorCodigo.add(producto);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Producto no encontrado: " + e.getMessage());
        }
        return productosPorCodigo;
    }

    public void eliminarProductoPorCodigo(String codigoProducto) throws Exception{
        try {
            String sql = "DELETE FROM producto WHERE codigo_producto = '" + codigoProducto + "'";
            insertarModificarEliminar(sql);
            System.out.println("El producto " + codigoProducto + " eliminado");
        } catch (Exception e) {
            throw new Exception("Producto no eliminado correctamente: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
    }

    public List<Producto> listarProductosPorProveedor(String proveedor) throws Exception {
        List<Producto> productosPorProveedor = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto WHERE proveedor = '" + proveedor + "'";
            consultarBase(sql);
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(resultSet.getString("codigo_producto"));
                producto.setNombreProducto(resultSet.getString("nombre"));
                producto.setIdGamaProducto(resultSet.getInt("id_gama"));
                producto.setDimensiones(resultSet.getString("dimensiones"));
                producto.setProveedor(resultSet.getString("proveedor"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setStock(resultSet.getInt("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
                productosPorProveedor.add(producto);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Producto no listados correctamente: " + e.getMessage());
        }
        return productosPorProveedor;
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            String sql = "UPDATE producto SET " +
                    "codigo_producto = '" + producto.getCodigoProducto() + "', " +
                    "nombre = '" + producto.getNombreProducto() + "', " +
                    "id_gama_producto = " + producto.getIdGamaProducto() + ", " +
                    "dimensiones = '" + producto.getDimensiones() + "', " +
                    "proveedor = '" + producto.getProveedor() + "', " +
                    "descripcion = '" + producto.getDescripcion() + "', " +
                    "cantidad_en_stock = " + producto.getStock() + ", " +
                    "precio_venta = " + producto.getPrecioVenta() + ", " +
                    "precio_proveedor = " + producto.getPrecioProveedor() +
                    " WHERE id_producto = " + producto.getIdProducto();
            insertarModificarEliminar(sql);
            System.out.println("El producto " + producto.getNombreProducto() + " ha sido modificado correctamente.");
        } catch (Exception e) {
            throw new Exception("Error al modificar el producto: " + e.getMessage());
        } finally {
            desconectarBaseDeDatos();
        }
    }


    public List<Producto> listarProductoConLaMenorCantidadDeStockDisponible() throws Exception {
        List<Producto> productosPorCantidadDisponible = new ArrayList<>();
        try {
            Producto producto = new Producto();
            String sql = "SELECT * FROM producto ORDER BY cantidad_en_stock ASC LIMIT 1";
            consultarBase(sql);
            while (resultSet.next()) {
                producto.setCodigoProducto(resultSet.getString("codigo_producto"));
                producto.setNombreProducto(resultSet.getString("nombre"));
                producto.setIdGamaProducto(resultSet.getInt("id_gama"));
                producto.setDimensiones(resultSet.getString("dimensiones"));
                producto.setProveedor(resultSet.getString("proveedor"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setStock(resultSet.getInt("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                producto.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
                productosPorCantidadDisponible.add(producto);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Producto no encontrado con la menor de cantidad de stock disponible: " + e.getMessage());
        }
        return productosPorCantidadDisponible;
    }

    public List<Producto> listarLosProductosConMenorPrecioVenta() throws Exception {
        List<Producto> productosPorCantidadDisponible = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto WHERE precio_venta = (SELECT MIN(precio_venta) FROM producto)";
            consultarBase(sql);
            while (resultSet.next()) {
                Producto p = new Producto();
                p.setCodigoProducto(resultSet.getString("codigo_producto"));
                p.setNombreProducto(resultSet.getString("nombre"));
                p.setIdGamaProducto(resultSet.getInt("id_gama"));
                p.setDimensiones(resultSet.getString("dimensiones"));
                p.setProveedor(resultSet.getString("proveedor"));
                p.setDescripcion(resultSet.getString("descripcion"));
                p.setStock(resultSet.getInt("cantidad_en_stock"));
                p.setPrecioVenta(resultSet.getDouble("precio_venta"));
                p.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
                productosPorCantidadDisponible.add(p);
            }
            desconectarBaseDeDatos();
        } catch (Exception e) {
            desconectarBaseDeDatos();
            throw new Exception("Error en listar los productos con menor precio de venta: " + e.getMessage());
        }
        return productosPorCantidadDisponible;
    }
}
