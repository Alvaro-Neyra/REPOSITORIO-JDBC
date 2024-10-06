package org.example.PATRON_DISENO_DAO.entidades;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int idGamaProducto;
    private String dimensiones;
    private double precioProveedor;
    private double precioVenta;
    private int stock;
    private String descripcion;

    public Producto(int idProducto, String nombreProducto, int idGamaProducto, String dimensiones, double precioProveedor, double precioVenta, int stock, String descripcion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idGamaProducto = idGamaProducto;
        this.dimensiones = dimensiones;
        this.precioProveedor = precioProveedor;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.descripcion = descripcion;
    }
    public Producto(String nombreProducto, int idGamaProducto, String dimensiones, double precioProveedor, double precioVenta, int stock, String descripcion) {
        this.nombreProducto = nombreProducto;
        this.idGamaProducto = idGamaProducto;
        this.dimensiones = dimensiones;
        this.precioProveedor = precioProveedor;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.descripcion = descripcion;
    }
    public Producto() {}

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
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
}