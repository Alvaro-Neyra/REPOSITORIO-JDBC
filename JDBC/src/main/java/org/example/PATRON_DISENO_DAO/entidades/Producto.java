package org.example.PATRON_DISENO_DAO.entidades;

import java.util.ResourceBundle;

public class Producto {
    private int idProducto;
    private String codigoProducto;
    private String nombreProducto;
    private int idGamaProducto;
    private String dimensiones;
    private String proveedor;
    private String descripcion;
    private int stock;
    private double precioVenta;
    private double precioProveedor;

    public Producto(int idProducto, String codigoProducto, String nombreProducto, int idGamaProducto, String dimensiones, String proveedor, String descripcion,  int stock, double precioVenta, double precioProveedor) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.idGamaProducto = idGamaProducto;
        this.dimensiones = dimensiones;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.precioProveedor = precioProveedor;
    }
    public Producto(String codigoProducto, String nombreProducto, int idGamaProducto, String dimensiones, String proveedor, String descripcion,  int stock, double precioVenta, double precioProveedor) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.idGamaProducto = idGamaProducto;
        this.dimensiones = dimensiones;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.precioProveedor = precioProveedor;
    }
    public Producto() {}

    public int getIdProducto() {
        return idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdGamaProducto() {
        return idGamaProducto;
    }

    public void setIdGamaProducto(int idGamaProducto) {
        this.idGamaProducto = idGamaProducto;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public double getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(double precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", idGamaProducto=" + idGamaProducto +
                ", dimensiones='" + dimensiones + '\'' +
                ", precioProveedor=" + precioProveedor +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Producto producto = (Producto) obj;

        return idProducto == producto.idProducto &&
                idGamaProducto == producto.idGamaProducto &&
                stock == producto.stock &&
                Double.compare(producto.precioVenta, precioVenta) == 0 &&
                Double.compare(producto.precioProveedor, precioProveedor) == 0 &&
                codigoProducto.equals(producto.codigoProducto) &&
                nombreProducto.equals(producto.nombreProducto) &&
                dimensiones.equals(producto.dimensiones) &&
                proveedor.equals(producto.proveedor) &&
                descripcion.equals(producto.descripcion);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(idProducto);
        result = 31 * result + codigoProducto.hashCode();
        result = 31 * result + nombreProducto.hashCode();
        result = 31 * result + Integer.hashCode(idGamaProducto);
        result = 31 * result + dimensiones.hashCode();
        result = 31 * result + proveedor.hashCode();
        result = 31 * result + descripcion.hashCode();
        result = 31 * result + Integer.hashCode(stock);
        long precioVentaBits = Double.doubleToLongBits(precioVenta);
        result = 31 * result + Long.hashCode(precioVentaBits);
        long precioProveedorBits = Double.doubleToLongBits(precioProveedor);
        result = 31 * result + Long.hashCode(precioProveedorBits);
        return result;
    }

}