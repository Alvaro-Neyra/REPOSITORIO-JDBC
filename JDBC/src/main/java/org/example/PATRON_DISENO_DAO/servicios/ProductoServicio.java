package org.example.PATRON_DISENO_DAO.servicios;

import org.example.PATRON_DISENO_DAO.entidades.Producto;
import org.example.PATRON_DISENO_DAO.persistencia.ProductoDAO;
import java.util.List;

public class ProductoServicio {
    private ProductoDAO productoDAO;
    public ProductoServicio() {
        productoDAO = new ProductoDAO();
    }
    public void agregarProducto(Producto producto) throws Exception {
        verificarDatosProducto(producto);
        productoDAO.guardarProducto(producto);
    }

    public List<Producto> listarProductos() throws Exception {
        List<Producto> productos = null;
        try {
            productos = productoDAO.listarProductos();
            if (productos.isEmpty()) {
                throw new Exception("No se encontro el producto");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return productos;
    }

    public List<Producto> buscarProductoPorCodigo(String codigo) throws Exception {
        verificarCodigoProducto(codigo);
        List<Producto> productos = null;
        try {
            productos = productoDAO.buscarProductoPorCodigo(codigo);
            if (productos.isEmpty()) {
                System.out.println("No se encontro el producto");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return productos;
    }

    public void eliminarProductoPorCodigo(String codigo) throws Exception {
        verificarCodigoProducto(codigo);
        productoDAO.eliminarProductoPorCodigo(codigo);
    }

    public List<Producto> productosPorProveedor(String proveedor) throws Exception {
        verificarCodigoProducto(proveedor);
        List<Producto> productos = null;
        try {
            productos = productoDAO.listarProductosPorProveedor(proveedor);
            if (productos.isEmpty()) {
                System.out.println("No se encontro el producto");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return productos;
    }

    public void modificarProducto(Producto producto) throws Exception {
        verificarDatosProducto(producto);
        productoDAO.modificarProducto(producto);
    }

    public List<Producto> listarProductoConElMenorStock() throws Exception {
        List<Producto> productos = null;
        try {
            productos = productoDAO.listarProductos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return productos;
    }

    public List<Producto> listarLosProductosConMenorPrecio() throws Exception {
        List<Producto> productos = null;
        try {
            productos = productoDAO.listarLosProductosConMenorPrecioVenta();
            if (productos.isEmpty()) {
                System.out.println("No hay productos existentes");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return productos;
    }

    private void verificarCodigoProducto(String codigoProducto) throws Exception {
        if (codigoProducto == null || codigoProducto.isEmpty()) {
            throw new Exception("Los atributos del producto no pueden ser vacio");
        }
    }

    private void verificarDatosProducto(Producto producto) throws Exception {
        if (producto.getIdProducto() < 1 ) {
            throw new Exception("No se puede agregar el producto");
        }
        if (producto.getNombreProducto() == null || producto.getNombreProducto().equals("")) {
            throw new Exception("El nombre del producto no puede ser vacio");
        }
        if (producto.getCodigoProducto() == null || producto.getCodigoProducto().equals("")) {
            throw new Exception("El codigo del producto no puede ser vacio");
        }
        if (producto.getIdGamaProducto() < 1) {
            throw new Exception("Id gama producto no valido");
        }
        if (producto.getDimensiones() == null || producto.getDimensiones().equals("")) {
            throw new Exception("El dimensiones del producto no puede ser vacio");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().equals("")) {
            throw new Exception("El descripcion del producto no puede ser vacio");
        }
        if (producto.getProveedor() == null || producto.getProveedor().equals("")) {
            throw new Exception("El proveedor no puede ser vacio");
        }
        if (producto.getStock() < 1) {
            throw new Exception("El stock del producto no puede ser vacio");
        }
        if (producto.getPrecioVenta() < 0.00) {
            throw new Exception("El precio del producto no puede ser vacio");
        }
        if (producto.getPrecioVenta() < 0.00) {
            throw new Exception("El precio del producto no puede ser vacio");
        }
    }
}
